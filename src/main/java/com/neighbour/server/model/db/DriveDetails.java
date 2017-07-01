package com.neighbour.server.model.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ajithpandel
 */
public class DriveDetails {
    private String timestamp;
    private String driverName;
    private String contactNumber;
    private String vehicleNumber;
    private String srcName;
    private String destinationName;
    private List<Passenger> passengers;

    public DriveDetails() {
        passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriveDetails that = (DriveDetails) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(driverName, that.driverName) && Objects
                .equals(contactNumber, that.contactNumber) && Objects.equals(vehicleNumber, that.vehicleNumber)
                && Objects.equals(srcName, that.srcName) && Objects.equals(destinationName, that.destinationName)
                && Objects.equals(passengers, that.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, driverName, contactNumber, vehicleNumber, srcName, destinationName, passengers);
    }

    @Override
    public String toString() {
        return "DriveDetails{" + "timestamp='" + timestamp + '\'' + ", driverName='" + driverName + '\''
                + ", contactNumber='" + contactNumber + '\'' + ", vehicleNumber='" + vehicleNumber + '\''
                + ", srcName='" + srcName + '\'' + ", destinationName='" + destinationName + '\'' + ", passengers="
                + passengers + '}';
    }
}
