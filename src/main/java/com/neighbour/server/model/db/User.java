package com.neighbour.server.model.db;

import com.neighbour.server.model.rest.SignUp;
import java.util.Objects;

/**
 * @author ajithpandel
 */
public class User extends SignUp {
    private Integer userId;
    private String token;
    private Integer pendingStatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(Integer pendingStatus) {
        this.pendingStatus = pendingStatus;
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(token, user.token) && Objects
                .equals(pendingStatus, user.pendingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, token, pendingStatus);
    }

    @Override
    public String toString() {
        return "User{" + "userId='" + userId + '\'' + ", token='" + token + '\'' + ", pendingStatus='" + pendingStatus
                + '\'' + '}';
    }
}
