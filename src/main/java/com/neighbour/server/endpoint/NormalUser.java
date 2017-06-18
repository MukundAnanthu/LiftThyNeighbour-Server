package com.neighbour.server.endpoint;

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
}
