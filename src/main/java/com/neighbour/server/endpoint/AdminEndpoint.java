package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.rest.LoginDetails;
import com.neighbour.server.model.rest.Tenant;
import com.neighbour.server.model.rest.UserType;
import com.neighbour.server.util.DBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class AdminEndpoint {

    @RequestMapping(value = "/api/getTenants", method = RequestMethod.POST)
    public Map<String, Object> getTenants(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<>();

        Integer userId = (Integer) body.get("userId");
        String token = (String)body.get("token");
        if (userId == null || token == null) {
            map.put("message", "userId or token is missing");
            return map;
        }

        Boolean a;
        try {
            a = DBHelper.validateToken(userId, token, UserType.ADMIN);
            if (!a) {
                map.put("message", "Invalid token");
                return map;
            }
            List<Tenant> list = DBHelper.getTenants(userId, false);
            map.put("tenantList", list);
            return map;
        } catch (DBException e) {
            map.put("message", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/api/pendingApprovalList", method = RequestMethod.POST)
    public Map<String, Object> getPending(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<>();

        Integer userId = (Integer) body.get("userId");
        String token = (String)body.get("token");
        if (userId == null || token == null) {
            map.put("message", "userId or token is missing");
            return map;
        }

        Boolean a;
        try {
            a = DBHelper.validateToken(userId, token, UserType.ADMIN);
            if (!a) {
                map.put("message", "Invalid token");
                return map;
            }
            List<Tenant> list = DBHelper.getTenants(userId, true);
            map.put("pendingList", list);
            return map;
        } catch (DBException e) {
            map.put("message", e.getMessage());
            return map;
        }
    }


}
