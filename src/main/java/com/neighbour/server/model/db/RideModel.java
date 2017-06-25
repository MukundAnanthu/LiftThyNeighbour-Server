package com.neighbour.server.model.db;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class RideModel {
    private Integer rideId;
    private Integer driverUserId;
    private Integer sourceType;
    private Integer sourceId;
    private Integer destinationId;
    private String departureTime;
    private Integer numberOfSeats;
    private Integer finishedRide;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Integer getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Integer driverUserId) {
        this.driverUserId = driverUserId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getFinishedRide() {
        return finishedRide;
    }

    public void setFinishedRide(Integer finishedRide) {
        this.finishedRide = finishedRide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RideModel rideModel = (RideModel) o;
        return Objects.equals(rideId, rideModel.rideId) && Objects.equals(driverUserId, rideModel.driverUserId)
                && Objects.equals(sourceType, rideModel.sourceType) && Objects.equals(sourceId, rideModel.sourceId)
                && Objects.equals(destinationId, rideModel.destinationId) && Objects
                .equals(departureTime, rideModel.departureTime) && Objects
                .equals(numberOfSeats, rideModel.numberOfSeats) && Objects.equals(finishedRide, rideModel.finishedRide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, driverUserId, sourceType, sourceId, destinationId, departureTime, numberOfSeats,
                finishedRide);
    }

    @Override
    public String toString() {
        return "RideModel{" + "rideId=" + rideId + ", driverUserId=" + driverUserId + ", sourceType=" + sourceType
                + ", sourceId=" + sourceId + ", destinationId=" + destinationId + ", departureTime='" + departureTime
                + '\'' + ", numberOfSeats=" + numberOfSeats + ", finishedRide=" + finishedRide + '}';
    }
}
