package com.neighbour.server.model.db;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class LocationModel {

    public static final String LOCATION_ID = "locationId";
    public static final String LOCATION_NAME = "locationName";
    public static final String LOCATION_TYPE = "locationType";
    public static final String DISTANCE = "distance";

    public static final Integer APARTMENT = 1;
    public static final Integer TECH_PARK = 0;

    private Integer locationId;
    private String locationName;
    private Integer locationType;
    private Integer distance;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationModel that = (LocationModel) o;
        return Objects.equals(locationId, that.locationId) && Objects.equals(locationName, that.locationName) && Objects
                .equals(locationType, that.locationType) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, locationType, distance);
    }

    @Override
    public String toString() {
        return "LocationModel{" + "locationId='" + locationId + '\'' + ", locationName='" + locationName + '\''
                + ", locationType='" + locationType + '\'' + ", distance=" + distance + '}';
    }
}
