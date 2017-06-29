package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class Ride extends BasicAuth {
    private Integer sourceType;
    private Integer techParkId;
    private String timestamp;
    private RideType type;
    private Integer numberOfSeats;


    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getTechParkId() {
        return techParkId;
    }

    public void setTechParkId(Integer techParkId) {
        this.techParkId = techParkId;
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
        return Objects.equals(sourceType, ride.sourceType) && Objects.equals(techParkId, ride.techParkId) && Objects
                .equals(timestamp, ride.timestamp) && type == ride.type && Objects
                .equals(numberOfSeats, ride.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sourceType, techParkId, timestamp, type, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Ride{" + "sourceType=" + sourceType + ", techParkId=" + techParkId + ", timestamp='" + timestamp + '\''
                + ", type=" + type + ", numberOfSeats=" + numberOfSeats + '}';
    }
}
