package com.neighbour.server.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author ajithpandel
 */
public class PasswordHelper {

    private static final Integer workload = 12;

    public static String hashPassword(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(workload));
    }

    public static Boolean checkPassword(String plainText, String hash) {
        return BCrypt.checkpw(plainText, hash);
    }
}
