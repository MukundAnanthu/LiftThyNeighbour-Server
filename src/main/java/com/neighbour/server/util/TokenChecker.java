package com.neighbour.server.util;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.Admin;
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
        return validateUser(auth, false);
    }

    public static Boolean validateUser(BasicAuth auth, Boolean isAdmin) throws DBException {
        String token;
        if (isAdmin) {
            Admin admin =  DBHelper.getAdminUser(auth.getUserId());
            if (admin == null) {
                return Boolean.FALSE;
            } else {
                token = admin.getToken();
            }
        } else {
            User user = DBHelper.getUser(auth.getUserId());
            if (user == null) {
                return Boolean.FALSE;
            } else {
                token = user.getToken();
            }
        }

        if (PasswordHelper.checkPassword(auth.getToken(), token)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
