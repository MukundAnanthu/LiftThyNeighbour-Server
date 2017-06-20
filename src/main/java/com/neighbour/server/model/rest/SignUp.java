package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class SignUp {
    private String userName;
    private String password;
    private Integer apartmentId;
    private String contactNumber;
    private String flatNumber;
    private String email;
    private String vehicleNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SignUp signUp = (SignUp) o;
        return Objects.equals(userName, signUp.userName) && Objects.equals(password, signUp.password) && Objects
                .equals(apartmentId, signUp.apartmentId) && Objects.equals(contactNumber, signUp.contactNumber)
                && Objects.equals(flatNumber, signUp.flatNumber) && Objects.equals(email, signUp.email) && Objects
                .equals(vehicleNumber, signUp.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, apartmentId, contactNumber, flatNumber, email, vehicleNumber);
    }

    @Override
    public String toString() {
        return "SignUp{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", apartmentId="
                + apartmentId + ", contactNumber='" + contactNumber + '\'' + ", flatNumber='" + flatNumber + '\''
                + ", email='" + email + '\'' + ", vehicleNumber='" + vehicleNumber + '\'' + '}';
    }
}
