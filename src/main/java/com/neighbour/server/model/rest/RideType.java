package com.neighbour.server.model.rest;

/**
 * @author ajithpandel
 */
public enum RideType {
    OFFER("OFFER"), TAKE("TAKE");

    private String rideType;

    private RideType(String rt) {
        this.rideType = rt;
    }

    public String getRideType() {
        return this.rideType;
    }

    public RideType fromString(String rt) {
        for (RideType rideType : RideType.values()) {
            if (rideType.getRideType().equals(rt)) {
                return rideType;
            }
        }

        throw new IllegalArgumentException("Cannot form rideType from " + rt + " . "
                + "Possible values are [OFFER, TAKE]");
    }
}
