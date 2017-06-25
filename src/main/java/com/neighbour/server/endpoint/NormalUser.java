package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.LocationModel;
import com.neighbour.server.model.rest.Ride;
import com.neighbour.server.model.rest.SignUp;
import com.neighbour.server.util.DBException;
import com.neighbour.server.util.TokenChecker;
import java.time.Instant;
import java.util.Date;
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

        LocationModel source = null;
        LocationModel dest = null;
        for (LocationModel location : locationList) {
            if (Objects.equals(ride.getSourceId(), location.getLocationId())) {
                source = location;
            } else if (Objects.equals(ride.getDestinationId(), location.getLocationId())) {
                dest = location;
            }
        }

        if (source == null || dest == null) {
            return "Invalid source or destination";
        }

        if (!Objects.equals(source.getLocationType(), ride.getSourceType())) {
            return "Invalid source type";
        }

        if (Objects.equals(dest.getLocationType(), source.getLocationType())) {
            return "Source and destination type should be different";
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
        if (ride.getSourceType() == null || ride.getSourceId() == null || ride.getDestinationId() == null
                || ride.getTimestamp() == null || ride.getType() == null || ride.getNumberOfSeats() == null) {
            return "Invalid values";
        }
        if (!TokenChecker.validateUser(ride)) {
            return "Invalid auth";
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

        if (ride.getSourceType() == null || ride.getSourceId() == null || ride.getDestinationId() == null
                || ride.getTimestamp() == null || ride.getType() == null) {
            return "Invalid values";
        }

        if (!TokenChecker.validateUser(ride)) {
            return "Invalid auth";
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

    @RequestMapping(value = "/api/ride", method = RequestMethod.POST)
    public Map<String, Object> ride(@RequestBody Ride body) throws DBException {
        Map<String, Object> map = new HashMap<>();

        switch (body.getType()) {
            case OFFER:
                String ret = validateRideOffer(body);
                if (ret != null) {
                    map.put("result", "FAILURE");
                    map.put("message", ret);
                    return map;
                }

                DBHelper.addRide(body);
                map.put("result", "SUCCESS");
                map.put("message", "Ride added successfully");
                return map;
            case TAKE:
                break;
            default:
                // error
        }
        map.put("body", body);
        return map;
    }
}
