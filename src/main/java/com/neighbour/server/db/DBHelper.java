package com.neighbour.server.db;

import com.neighbour.server.endpoint.Location;
import com.neighbour.server.model.LocationModel;
import com.neighbour.server.util.DBException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
