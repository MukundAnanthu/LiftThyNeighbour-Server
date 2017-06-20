package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.rest.SignUp;
import com.neighbour.server.util.DBException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class NormalUser {

    @RequestMapping(value = "/api/testing", method = RequestMethod.POST)
    public Map<String, Object> testing(@RequestHeader("token") String token, @RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<>();
        map.putAll(body);
        map.put("token", token);
        return map;
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public Map<String, Object> signup(@RequestBody SignUp body) {
        Map<String, Object> map = new HashMap<>();
        try {
            Boolean exists = DBHelper.checkUserExists(body);
            if (!exists) {
                Integer r = DBHelper.addUser(body);
                if (r == 1) {
                    map.put("result", "SUCCESS");
                    map.put("message", "Sign Up successfull");
                } else {
                    map.put("result", "FAILURE");
                    map.put("message", "Internal error");
                }
            } else {
                map.put("result", "FAILURE");
                map.put("message", "userName or contactNumber already taken");
            }
        } catch (DBException e) {
            map.put("result", "FAILURE");
            map.put("message", e.getMessage());
        }

        return map;
    }
}
