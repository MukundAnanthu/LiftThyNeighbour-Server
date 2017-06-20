package com.neighbour.server.endpoint;

import com.neighbour.server.db.DBHelper;
import com.neighbour.server.model.LocationModel;
import com.neighbour.server.util.DBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ajithpandel
 */
@RestController
public class Location {
    List<Map<String, String>> filterLocationsGeneric(List<LocationModel> list, Integer type) {
        List<Map<String, String>> list1 = new ArrayList<>();
        for (LocationModel model : list) {
            Map<String, String> map = new HashMap<>();
            if (Objects.equals(model.getLocationType(), type)) {
                map.put("id", model.getLocationId().toString());
                map.put("name", model.getLocationName());
            }
            if (!map.isEmpty()) {
                list1.add(map);
            }
        }
        return list1;
    }

    @RequestMapping(value = "/api/getApartmentList")
    public Map<String, Object> getApartmentList() throws DBException {
        List<Map<String, String>> list =  filterLocationsGeneric(DBHelper.getLocationList(), LocationModel.APARTMENT);
        Map<String, Object> ret = new HashMap<>();
        ret.put("apartmentList", list);
        return ret;
    }

    @RequestMapping(value = "/api/getTechParkList")
    public Map<String, Object> getTechParkList() throws DBException {
        List<Map<String, String>> list =  filterLocationsGeneric(DBHelper.getLocationList(), LocationModel.TECH_PARK);
        Map<String, Object> ret = new HashMap<>();
        ret.put("techParkList", list);
        return ret;
    }
}
