package com.neighbour.server.util;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.User;
import com.neighbour.server.model.rest.BasicAuth;
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

    public static Boolean validateUser(BasicAuth auth) throws DBException {
        User user = DBHelper.getUser(auth.getUserId());
        if (user != null && PasswordHelper.checkPassword(auth.getToken(), user.getToken())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
