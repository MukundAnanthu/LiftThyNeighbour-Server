package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class Ride extends BasicAuth {
    private Integer sourceType;
    private Integer sourceId;
    private Integer destinationId;
    private String timestamp;
    private RideType type;
    private Integer numberOfSeats;


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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public RideType getType() {
        return type;
    }

    public void setType(RideType type) {
        this.type = type;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Ride ride = (Ride) o;
        return Objects.equals(sourceType, ride.sourceType) && Objects.equals(sourceId, ride.sourceId) && Objects
                .equals(destinationId, ride.destinationId) && Objects.equals(timestamp, ride.timestamp)
                && type == ride.type && Objects.equals(numberOfSeats, ride.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceType, sourceId, destinationId, timestamp, type, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Ride{" + "sourceType=" + sourceType + ", sourceId=" + sourceId + ", destinationId=" + destinationId
                + ", timestamp='" + timestamp + '\'' + ", type=" + type + ", numberOfSeats=" + numberOfSeats + '}';
    }
}
