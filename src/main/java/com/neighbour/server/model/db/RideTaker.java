package com.neighbour.server.model.db;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class RideTaker {
    private Integer rideId;
    private Integer takerUserId;
    private Integer techParkId;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Integer getTakerUserId() {
        return takerUserId;
    }

    public void setTakerUserId(Integer takerUserId) {
        this.takerUserId = takerUserId;
    }

    public Integer getTechParkId() {
        return techParkId;
    }

    public void setTechParkId(Integer techParkId) {
        this.techParkId = techParkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RideTaker rideTaker = (RideTaker) o;
        return Objects.equals(rideId, rideTaker.rideId) && Objects.equals(takerUserId, rideTaker.takerUserId) && Objects
                .equals(techParkId, rideTaker.techParkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, takerUserId, techParkId);
    }

    @Override
    public String toString() {
        return "RideTaker{" + "rideId='" + rideId + '\'' + ", takerUserId='" + takerUserId + '\'' + ", techParkId='"
                + techParkId + '\'' + '}';
    }
}
