package com.neighbour.server.model;

/**
 * @author ajithpandel
 */
public enum UserType {
    ADMIN("ADMIN"), USER("USER");

    private String type;

    private UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserType fromString(String type) {
        for (UserType t : UserType.values()) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown type");
    }
}
