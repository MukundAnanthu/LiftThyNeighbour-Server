package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class DeleteProfile extends BasicAuth {
    private Integer userIdToDelete;

    public Integer getUserIdToDelete() {
        return userIdToDelete;
    }

    public void setUserIdToDelete(Integer userIdToDelete) {
        this.userIdToDelete = userIdToDelete;
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
        DeleteProfile that = (DeleteProfile) o;
        return Objects.equals(userIdToDelete, that.userIdToDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userIdToDelete);
    }

    @Override
    public String toString() {
        return "DeleteProfile{" + "userIdToDelete='" + userIdToDelete + '\'' + '}';
    }
}
