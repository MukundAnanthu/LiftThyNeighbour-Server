package com.neighbour.server.model.db;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class Admin {
    private Integer adminId;
    private String adminName;
    private String password;
    private String token;
    private Integer locationId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) && Objects.equals(adminName, admin.adminName) && Objects
                .equals(password, admin.password) && Objects.equals(token, admin.token) && Objects
                .equals(locationId, admin.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminName, password, token, locationId);
    }

    @Override
    public String toString() {
        return "Admin{" + "adminId=" + adminId + ", adminName='" + adminName + '\'' + ", password='" + password + '\''
                + ", token='" + token + '\'' + ", locationId=" + locationId + '}';
    }
}
