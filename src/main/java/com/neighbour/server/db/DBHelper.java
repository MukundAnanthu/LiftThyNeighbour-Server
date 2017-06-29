package com.neighbour.server.db;

import com.neighbour.server.model.db.Admin;
import com.neighbour.server.model.db.LocationModel;
import com.neighbour.server.model.db.RideModel;
import com.neighbour.server.model.db.User;
import com.neighbour.server.model.rest.Ride;
import com.neighbour.server.model.rest.SignUp;
import com.neighbour.server.model.rest.Tenant;
import com.neighbour.server.model.rest.UserType;
import com.neighbour.server.util.DBException;
import com.neighbour.server.util.PasswordHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ajithpandel
 */
public class DBHelper {

    private static Connection conn;

    private static String getConnectionString() {
        StringBuilder builder = new StringBuilder();
        builder.append("jdbc:mysql://");
        builder.append(System.getenv("DBHOST"));
        builder.append(":");
        builder.append(System.getenv("DBPORT"));
        builder.append("/");
        builder.append(System.getenv("DATABASE"));
        builder.append("?useSSL=false");
        return builder.toString();
    }

    private static String getDbUser() {
        return System.getenv("DBUSER");
    }

    private static String getDbPassword() {
        return System.getenv("DBPASSWORD");
    }

    public static Connection getConnection() throws DBException {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(getConnectionString(), getDbUser(), getDbPassword());

            }

            return conn;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Statement getStatement() throws DBException {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws DBException {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    public static String pingDb() throws DBException {
        try {
            Statement stmt = getStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1");
            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return "Unable to connect";
    }

    public static List<LocationModel> getLocationList() throws DBException {
        Statement stmt = getStatement();
        try {
            List<LocationModel> list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT * from location");
            while (rs.next()) {
                LocationModel loc = new LocationModel();
                loc.setDistance(rs.getInt(LocationModel.DISTANCE));
                loc.setLocationId(rs.getInt(LocationModel.LOCATION_ID));
                loc.setLocationName(rs.getString(LocationModel.LOCATION_NAME));
                loc.setLocationType(rs.getInt(LocationModel.LOCATION_TYPE));

                list.add(loc);
            }

            return list;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Integer addUser(SignUp user) throws DBException {
        try {
            String sqlString = "INSERT INTO user "
                    + "(userName, password, apartmentId, flatNumber, contactNumber, email, vehicleNumber) "
                    + "VALUES " + "(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, PasswordHelper.hashPassword(user.getPassword()));
            stmt.setInt(3, user.getApartmentId());
            stmt.setString(4, user.getFlatNumber());
            stmt.setString(5, user.getContactNumber());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getVehicleNumber());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Boolean checkUserExists(SignUp user) throws DBException {
        try {
            String sqlString = "select count(*) as count from user where userName=? or contactNumber=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getContactNumber());

            ResultSet rs = stmt.executeQuery();
            rs.next();
            Integer count = rs.getInt("count");
            if (count == 0) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static User getUser(String userName) throws DBException {
        try {
            String sqlString = "select * from user where userName=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            Boolean a = rs.next();
            if (!a) {
                return null;
            }

            User user = new User();
            user.setPendingStatus(rs.getInt("pendingStatus"));
            user.setToken(rs.getString("token"));
            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setApartmentId(rs.getInt("apartmentId"));
            user.setContactNumber(rs.getString("contactNumber"));
            user.setFlatNumber(rs.getString("flatNumber"));
            user.setEmail(rs.getString("email"));
            user.setVehicleNumber(rs.getString("vehicleNumber"));

            return user;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static User getUser(Integer userId) throws DBException {
        try {
            String sqlString = "select * from user where userId=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            Boolean a = rs.next();
            if (!a) {
                return null;
            }

            User user = new User();
            user.setPendingStatus(rs.getInt("pendingStatus"));
            user.setToken(rs.getString("token"));
            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setApartmentId(rs.getInt("apartmentId"));
            user.setContactNumber(rs.getString("contactNumber"));
            user.setFlatNumber(rs.getString("flatNumber"));
            user.setEmail(rs.getString("email"));
            user.setVehicleNumber(rs.getString("vehicleNumber"));

            return user;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Admin getAdminUser(String adminName) throws DBException {
        try {
            String sqlString = "select * from admin where adminName=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setString(1, adminName);
            ResultSet rs = stmt.executeQuery();


            Boolean a = rs.next();
            if (!a) {
                return null;
            }
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("adminId"));
            admin.setAdminName(rs.getString("adminName"));
            admin.setPassword(rs.getString("password"));
            admin.setToken(rs.getString("token"));
            admin.setLocationId(rs.getInt("locationId"));

            return admin;
        } catch (SQLException e) {
            throw new DBException(e);
        }

    }

    public static Admin getAdminUser(Integer adminId) throws DBException {
        try {
            String sqlString = "select * from admin where adminId=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();


            Boolean a = rs.next();
            if (!a) {
                return null;
            }
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("adminId"));
            admin.setAdminName(rs.getString("adminName"));
            admin.setPassword(rs.getString("password"));
            admin.setToken(rs.getString("token"));
            admin.setLocationId(rs.getInt("locationId"));

            return admin;
        } catch (SQLException e) {
            throw new DBException(e);
        }

    }

    public static void updateToken(Integer userId, String token, UserType type) throws DBException{
        try {
            String sqlString = "update user set token=? where userId=?;";
            if (type == UserType.ADMIN) {
                sqlString = "update admin set token=? where adminId=?;";
            }
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setString(1, token);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Boolean validateToken(Integer userId, String token, UserType type) throws DBException {
        String hashedToken = "";
        switch (type) {

            case ADMIN:
                Admin admin = DBHelper.getAdminUser(userId);
                if (admin == null) {
                    throw new DBException("Invalid user");
                }
                hashedToken = admin.getToken();
                break;
            case USER:
                User user = DBHelper.getUser(userId);
                if (user == null) {
                    throw new DBException("Invalid user");
                }
                hashedToken = user.getToken();
                break;
        }

        if (PasswordHelper.checkPassword(token, hashedToken)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static List<Tenant> getTenants(Integer userId, Boolean pending) throws DBException {
        Admin admin = getAdminUser(userId);

        try {
            String sqlString = "select * from user where apartmentId=?;";
            if (pending) {
                sqlString = "select * from user where apartmentId=? and pendingStatus=1;";
            }
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, admin.getLocationId());
            ResultSet rs = stmt.executeQuery();

            List<Tenant> list = new ArrayList<>();
            while (rs.next()) {
                Tenant tenant = new Tenant();
                tenant.setUserId(rs.getInt("userId"));
                tenant.setUserName(rs.getString("userName"));
                tenant.setFlatNumber(rs.getString("flatNumber"));
                tenant.setVehicleNumber(rs.getString("vehicleNumber"));
                tenant.setContactNumber(rs.getString("contactNumber"));
                tenant.setEmail(rs.getString("email"));
                list.add(tenant);
            }

            return list;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void clearPendingStatus(Integer userId) throws DBException {
        try {
            String sqlString = "UPDATE user set pendingStatus=0 where userId=?";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void deleteUser(Integer userId) throws DBException {
        try {
            String sqlString = "DELETE FROM user where userId=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void addRide(Ride ride) throws DBException {
        try {
            String sqlString = "INSERT INTO rideOffer "
                    + "(driverUserId, sourceType, sourceId, destinationId, departureTime, numberOfSeats) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?)";

            User user = DBHelper.getUser(ride.getUserId());
            if (user == null) {
                throw new DBException("Invalid userId");
            }
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, ride.getUserId());
            stmt.setInt(2, ride.getSourceType());
            if (ride.getSourceType() == LocationModel.APARTMENT) {
                stmt.setInt(3, user.getApartmentId());
                stmt.setInt(4, ride.getTechParkId());
            } else {
                stmt.setInt(3, ride.getTechParkId());
                stmt.setInt(4, user.getApartmentId());
            }
            stmt.setString(5, ride.getTimestamp());
            stmt.setInt(6, ride.getNumberOfSeats());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static List<RideModel> getRides() throws DBException {
        try {
            String sqlString = "SELECT * from rideOffer where finishedRide=0;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            ResultSet rs = stmt.executeQuery();

            List<RideModel> list = new ArrayList<>();
            while (rs.next()) {
                RideModel ride = new RideModel();
                ride.setRideId(rs.getInt("rideId"));
                ride.setDriverUserId(rs.getInt("driverUserId"));
                ride.setSourceType(rs.getInt("sourceType"));
                ride.setSourceId(rs.getInt("sourceId"));
                ride.setDestinationId(rs.getInt("destinationId"));
                ride.setDepartureTime(rs.getString("departureTime"));
                ride.setNumberOfSeats(rs.getInt("numberOfSeats"));
                ride.setFinishedRide(rs.getInt("finishedRide"));
                list.add(ride);
            }

            return list;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Integer getPassengerCount(Integer rideId) throws DBException {
        try {
            String sqlString = "SELECT COUNT(*) as COUNT from rideTaker where rideId=?;";
            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, rideId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("COUNT");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void addPassenger(Integer rideId, Integer takerId, Integer techParkId) throws DBException {
        try {
            String sqlString = "INSERT INTO rideTaker "
                    + "(rideId, takerUserId, techParkId) "
                    + "VALUES "
                    + "(?, ?, ?)";

            PreparedStatement stmt = getPreparedStatement(sqlString);
            stmt.setInt(1, rideId);
            stmt.setInt(2, takerId);
            stmt.setInt(3, techParkId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
