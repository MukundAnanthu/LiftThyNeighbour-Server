package com.neighbour.server.model.rest;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class LoginDetails {

    private String userName;
    private String password;
    private UserType userType;

    public LoginDetails() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginDetails that = (LoginDetails) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password)
                && userType == that.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, userType);
    }

    @Override
    public String toString() {
        return "LoginDetails{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", userType="
                + userType + '}';
    }
}
