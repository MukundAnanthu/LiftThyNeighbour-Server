package com.neighbour.server.model.db;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class Passenger {
    private String userName;
    private String contactNumber;
    private String srcName;
    private String destinationName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) o;
        return Objects.equals(userName, passenger.userName) && Objects.equals(contactNumber, passenger.contactNumber)
                && Objects.equals(srcName, passenger.srcName) && Objects
                .equals(destinationName, passenger.destinationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, contactNumber, srcName, destinationName);
    }

    @Override
    public String toString() {
        return "Passenger{" + "userName='" + userName + '\'' + ", contactNumber='" + contactNumber + '\''
                + ", srcName='" + srcName + '\'' + ", destinationName='" + destinationName + '\'' + '}';
    }
}
