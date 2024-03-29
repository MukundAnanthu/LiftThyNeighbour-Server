package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.db.User;
import com.neighbour.server.model.rest.DeleteProfile;
import com.neighbour.server.model.rest.LoginDetails;
import com.neighbour.server.model.rest.Tenant;
import com.neighbour.server.model.rest.UserType;
import com.neighbour.server.util.DBException;
import com.neighbour.server.util.TokenChecker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class AdminEndpoint {
    private static final Logger logger = Logger.getLogger(AdminEndpoint.class.getName());

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
            logger.info(map);
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
            logger.info(map);
            return map;
        } catch (DBException e) {
            map.put("message", e.getMessage());
            logger.info(e);
            return map;
        }
    }

    @RequestMapping(value = "/api/approve", method = RequestMethod.POST)
    public Map<String, Object> approve(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer userId = (Integer) body.get("userId");
            String token = (String)body.get("token");
            if (userId == null || token == null) {
                throw new DBException("userId or token is missing");
            }

            Boolean a;

            a = DBHelper.validateToken(userId, token, UserType.ADMIN);
            if (!a) {
                throw new DBException("Invalid token");
            }

            Integer id = (Integer)body.get("userIdToApprove");
            String approved = (String)body.get("approved");
            Boolean ap;
            if ("yes".equals(approved)) {
                ap = Boolean.TRUE;
            } else if ("no".equals(approved)){
                ap = Boolean.FALSE;
            } else {
                throw new DBException("Wrong value for approved. [yes, no]");
            }

            User user = DBHelper.getUser(id);
            if (user == null) {
                throw new DBException("Invalid user");
            }

            if (ap) {
                DBHelper.clearPendingStatus(id);
            } else {
                DBHelper.deleteUser(id);
            }
            map.put("message", "Operation completed successfully");
        } catch (DBException e) {

            logger.info(e);
            map.put("message", e.getMessage());
        }

        return map;
    }

    @RequestMapping(value = "/api/deleteProfile", method = RequestMethod.POST)
    public Map<String, Object> deleteProfile(@RequestBody DeleteProfile body) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (!TokenChecker.validateUser(body, true)) {
                map.put("result", "FAILURE");
                map.put("message", "Invalid auth");
            } else {
                DBHelper.deleteUser(body.getUserIdToDelete());
                map.put("result", "SUCCESS");
                map.put("message", "Successfully deleted");
            }
        } catch (DBException e) {
            map.put("result", "FAILURE");
            map.put("message", e.getMessage());
        }
        logger.info(map);
        return map;
    }

}
