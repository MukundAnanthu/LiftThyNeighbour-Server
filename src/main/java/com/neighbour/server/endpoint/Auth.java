package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.Admin;
import com.neighbour.server.model.db.User;
import com.neighbour.server.model.rest.LoginDetails;
import com.neighbour.server.util.DBException;
import com.neighbour.server.util.PasswordHelper;
import com.neighbour.server.util.TokenChecker;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class Auth {

    private static final String RESULT = "result";
    private static final String SUCCESS_RESULT = "SUCCESS";
    private static final String FAILURE_RESULT = "FAILURE";
    private static final String TOKEN = "token";
    private static final String USERID = "userId";

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Map<String, Object> auth(LoginDetails details) throws DBException {
        Map<String, Object> map = new HashMap<>();
        String hashedPassword = "";
        Integer userId = null;
        switch (details.getUserType()) {

            case ADMIN:
                Admin admin = DBHelper.getAdminUser(details.getUserName());
                if (admin == null) {
                    throw new DBException("Invalid user");
                }
                hashedPassword = admin.getPassword();
                userId = admin.getAdminId();
                break;
            case USER:
                User user = DBHelper.getUser(details.getUserName());
                if (user == null) {
                    throw new DBException("Invalid user");
                }
                hashedPassword = user.getPassword();
                userId = user.getUserId();
                break;
        }

        if (PasswordHelper.checkPassword(details.getPassword(), hashedPassword)) {
            String token = generateToken();
            DBHelper.updateToken(userId, PasswordHelper.hashPassword(token), details.getUserType());
            TokenChecker.setToken(token);
            map.put(RESULT, SUCCESS_RESULT);
            map.put(TOKEN, token);
            map.put(USERID, userId);
        } else {
            map.put(RESULT, FAILURE_RESULT);
        }
        return map;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody LoginDetails details) {
        try {
            return auth(details);
        } catch (DBException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("result", "FAILED");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.POST)
    public Map<String, Object> logout(@RequestHeader("token") String token) {
        Map<String, Object> map = new HashMap<>();
        if (TokenChecker.check(token)) {
            TokenChecker.resetToken();
            map.put(RESULT, SUCCESS_RESULT);
        } else {
            map.put(RESULT, FAILURE_RESULT);
        }

        return map;
    }
}
