package com.neighbour.server.util;

import java.util.Objects;

/**
 * @author ajithpandel
 */
public class TokenChecker {
    private static String token;

    public static Boolean check(String toCheck) {
        if (Objects.nonNull(token) && token.equals(toCheck)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static void setToken(String toSet) {
        token = toSet;
    }

    public static void resetToken() {
        token = null;
    }
}
