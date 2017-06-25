package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class BasicAuth {
    private Integer userId;
    private String token;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicAuth basicAuth = (BasicAuth) o;
        return Objects.equals(userId, basicAuth.userId) && Objects.equals(token, basicAuth.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, token);
    }

    @Override
    public String toString() {
        return "BasicAuth{" + "userId='" + userId + '\'' + ", token='" + token + '\'' + '}';
    }
}
