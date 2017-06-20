package com.neighbour.server.db;

import com.neighbour.server.model.db.LocationModel;
import com.neighbour.server.model.rest.SignUp;
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
                    + "(userName, password, apartmentId, flatNumber, contactNumber, emailId, vehicleNumber) "
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
}
