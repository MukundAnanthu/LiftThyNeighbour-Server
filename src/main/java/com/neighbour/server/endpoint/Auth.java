package com.neighbour.server.endpoint;

import com.neighbour.server.model.rest.LoginDetails;
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

    /*
    The hash for 'something' used for testing
     */
    private static final String tempPasswordForChecking = "$2a$12$7KswBeH7cuSbB2JoZJHNGOaX5YcGIdN7WIUWqmwYA6PaAzmKEYHKq";


    private static final String RESULT = "result";
    private static final String SUCCESS_RESULT = "SUCCESS";
    private static final String FAILURE_RESULT = "FAILURE";
    private static final String TOKEN = "token";

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Map<String, Object> auth(LoginDetails details) {
        Map<String, Object> map = new HashMap<>();
        if (PasswordHelper.checkPassword(details.getPassword(), tempPasswordForChecking)) {
            String token = generateToken();
            TokenChecker.setToken(token);
            map.put(RESULT, SUCCESS_RESULT);
            map.put(TOKEN, token);
        } else {
            map.put(RESULT, FAILURE_RESULT);
        }
        return map;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody LoginDetails details) {
        return auth(details);
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
