package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class Tenant {
    private Integer userId;
    private String userName;
    private String flatNumber;
    private String vehicleNumber;
    private String contactNumber;
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tenant tenant = (Tenant) o;
        return Objects.equals(userId, tenant.userId) && Objects.equals(userName, tenant.userName) && Objects
                .equals(flatNumber, tenant.flatNumber) && Objects.equals(vehicleNumber, tenant.vehicleNumber) && Objects
                .equals(contactNumber, tenant.contactNumber) && Objects.equals(email, tenant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, flatNumber, vehicleNumber, contactNumber, email);
    }

    @Override
    public String toString() {
        return "Tenant{" + "userId=" + userId + ", userName='" + userName + '\'' + ", flatNumber='" + flatNumber + '\''
                + ", vehicleNumber='" + vehicleNumber + '\'' + ", contactNumber='" + contactNumber + '\'' + ", email='"
                + email + '\'' + '}';
    }
}
