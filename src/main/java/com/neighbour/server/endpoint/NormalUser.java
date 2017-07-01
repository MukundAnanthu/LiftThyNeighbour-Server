package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.DriveDetails;
import com.neighbour.server.model.db.LocationModel;
import com.neighbour.server.model.db.RideModel;
import com.neighbour.server.model.db.User;
import com.neighbour.server.model.rest.BasicAuth;
import com.neighbour.server.model.rest.Ride;
import com.neighbour.server.model.rest.SignUp;
import com.neighbour.server.util.DBException;
import com.neighbour.server.util.TokenChecker;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class NormalUser {

    @RequestMapping(value = "/api/testing", method = RequestMethod.POST)
    public Map<String, Object> testing(@RequestHeader("token") String token, @RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<>();
        map.putAll(body);
        map.put("token", token);
        return map;
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public Map<String, Object> signup(@RequestBody SignUp body) {
        Map<String, Object> map = new HashMap<>();
        try {
            Boolean exists = DBHelper.checkUserExists(body);
            if (!exists) {
                Integer r = DBHelper.addUser(body);
                if (r == 1) {
                    map.put("result", "SUCCESS");
                    map.put("message", "Sign Up successfull");
                } else {
                    map.put("result", "FAILURE");
                    map.put("message", "Internal error");
                }
            } else {
                map.put("result", "FAILURE");
                map.put("message", "userName or contactNumber already taken");
            }
        } catch (DBException e) {
            map.put("result", "FAILURE");
            map.put("message", e.getMessage());
        }

        return map;
    }

    private String validateSrcAndDest(Ride ride) throws DBException {
        List<LocationModel> locationList = DBHelper.getLocationList();

        LocationModel techPark = null;
        for (LocationModel location : locationList) {
            if (Objects.equals(ride.getTechParkId(), location.getLocationId())) {
                techPark = location;
            }
        }

        // Validate that that the techParkId is a location
        // and a techPark
        if (techPark == null || (techPark.getLocationType() != LocationModel.TECH_PARK)) {
            return "Invalid techPark";
        }

        return null;
    }

    private String validateTime(String timestamp) {
        // Assumes timestamp is time in seconds in utc
        Long time = Long.valueOf(timestamp);
        Long now = Instant.now().getEpochSecond();

        // One hour check
        if (!(time - now >= 60 * 60)) {
            return "Ride should be offered or taken atleast one hour before";
        }

        return null;
    }

    private String validateRideOffer(Ride ride) throws DBException {
        if (ride.getSourceType() == null || ride.getTechParkId() == null
                || ride.getTimestamp() == null || ride.getType() == null || ride.getNumberOfSeats() == null) {
            return "Invalid values";
        }

        String temp = validateSrcAndDest(ride);
        if (temp != null) {
            return temp;
        }
        temp = validateTime(ride.getTimestamp());

        if (ride.getNumberOfSeats() < 1 || ride.getNumberOfSeats() > 20) {
            return "Invalid number of seats";
        }

        return null;
    }

    private String validateRideTaking(Ride ride) throws DBException {

        if (ride.getSourceType() == null || ride.getTechParkId() == null
                || ride.getTimestamp() == null || ride.getType() == null) {
            return "Invalid values";
        }

        String temp = validateSrcAndDest(ride);
        if (temp != null) {
            return temp;
        }

        temp = validateTime(ride.getTimestamp());
        if (temp != null) {
            return temp;
        }

        return null;
    }

    private Boolean checkValidLocations(RideModel driver, Ride taker, User rideUser, List<LocationModel> llist) {
        Integer dSrcId = driver.getSourceId();
        Integer dDestId = driver.getDestinationId();
        Integer tSrcId;
        Integer tDestId;
        if (taker.getSourceType() == LocationModel.APARTMENT) {
            tSrcId = rideUser.getApartmentId();
            tDestId = taker.getTechParkId();
        } else {
            tSrcId = taker.getTechParkId();
            tDestId = rideUser.getApartmentId();
        }

        Integer dSrcDist = null, dDestDist = null, tSrcDist = null, tDestDist = null;
        for (LocationModel location : llist) {
            Integer id = location.getLocationId();
            Integer dist = location.getDistance();
            if (id == dSrcId) {
                dSrcDist = dist;
            }

            if (id == dDestId) {
                dDestDist = dist;
            }

            if (id == tSrcId) {
                tSrcDist = dist;
            }

            if (id == tDestId) {
                tDestDist = dist;
            }
        }
        // driver source <= ride source <= ride dest <= driver dest
        if (dSrcDist == null || dDestDist == null || tSrcDist == null || tDestDist == null) {
            return Boolean.FALSE;
        }

        if (dSrcDist <= tSrcDist && tSrcDist <= tDestDist && tDestDist <= dDestDist) {
            return Boolean.TRUE;
        }

        if (dSrcDist >= tSrcDist && tSrcDist >= tDestDist && tDestDist >= dDestDist) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    private Boolean checkValidTime(String driverTime, String takerTime) {
        Long d = Long.valueOf(driverTime);
        Long t = Long.valueOf(takerTime);

        Long interval = 15 * 60L; // 15 minutes

        Long diff = d - t;
        if (diff < 0) {
            diff = -diff;
        }

        if (diff < interval) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private Map<String, Object> takeRide(Ride ride) throws DBException {
        Map<String, Object> map = new HashMap<>();
        List<RideModel> rides = DBHelper.getRides();
        List<LocationModel> locationList = DBHelper.getLocationList();
        User user = DBHelper.getUser(ride.getUserId());
        if (user == null) {
            throw new DBException("Invalid userId");
        }

        for (RideModel r : rides) {
            if (!checkValidLocations(r, ride, user, locationList)) {
                continue;
            }

            if (!checkValidTime(r.getDepartureTime(), ride.getTimestamp())) {
                continue;
            }

            Integer available = DBHelper.getPassengerCount(r.getRideId());
            if (available < r.getNumberOfSeats()) {
                // Done!
                DBHelper.addPassenger(r.getRideId(), ride.getUserId(), ride.getTechParkId());

                map.put("result", "SUCCESS");
                User u = DBHelper.getUser(r.getDriverUserId());
                if (u == null) {
                    throw new DBException("Unexpected error");
                }
                map.put("driverName", u.getUserName());
                map.put("contactNumber", u.getContactNumber());
                map.put("vehicleNumber", u.getVehicleNumber());
                map.put("departureTime", r.getDepartureTime());
                return map;
            }
        }
        map.put("result", "FAILURE");
        map.put("message", "No available rides");

        return map;
    }

    private Map<String, Object> returnFailureMap(Object reason) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "FAILURE");
        map.put("message", reason);

        return map;
    }

    @RequestMapping(value = "/api/ride", method = RequestMethod.POST)
    public Map<String, Object> ride(@RequestBody Ride body) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (!TokenChecker.validateUser(body)) {
                return returnFailureMap("Invalid auth");
            }
            String ret;
            switch (body.getType()) {
                case OFFER:
                    ret = validateRideOffer(body);
                    if (ret != null) {
                        return returnFailureMap(ret);
                    }

                    DBHelper.addRide(body);
                    map.put("result", "SUCCESS");
                    map.put("message", "Ride added successfully");
                    return map;
                case TAKE:
                    ret = validateRideTaking(body);
                    if (ret != null) {
                        return returnFailureMap(ret);
                    }
                    return takeRide(body);
                default:
                    return returnFailureMap("Wrong ride type");
            }
        } catch (DBException e) {
            return returnFailureMap(e.getMessage());
        }
    }

    @RequestMapping(value = "/api/futureRides", method = RequestMethod.POST)
    public Map<String, Object> futureRides(@RequestBody BasicAuth auth) {
        try {
            if (!TokenChecker.validateUser(auth)) {
                return returnFailureMap("Invalid auth");
            }

            List<DriveDetails> list = DBHelper.getAllFutureRides(auth.getUserId());
            Map<String, Object> map = new HashMap<>();
            map.put("rideList", list);
            return map;
        } catch (DBException e) {
            return returnFailureMap(e.getMessage());
        }
    }

}
