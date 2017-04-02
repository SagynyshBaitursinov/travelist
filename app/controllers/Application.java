package controllers;

import dto.LocationDto;
import models.Location;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    private static Integer background = 0;

    @Before
    public static void addBackground() {
        String backgroundStr = (background % 5) + "";
        background++;
        renderArgs.put("backgroundStr", backgroundStr);
    }

    public static void index() {
        render();
    }

    public static void test() {
        render();
    }

    public static void push(String[] geo, Integer climate, String[] activities, Integer money) {
        List<Location> locations = Location.findAll();
        List<Location> result = new ArrayList<Location>();
        for (Location location: locations) {
            boolean geographyValid = false;
            boolean activitiesValid = false;
            for (int i = 0; i < geo.length; i++) {
                if (location.geography.contains(geo[i])) {
                    geographyValid = true;
                    break;
                }
            }
            for (int i = 0; i < activities.length; i++) {
                if (location.activities.contains(activities[i])) {
                    activitiesValid = true;
                    break;
                }
            }
            if (geographyValid && activitiesValid) {
                result.add(location);
            }
        }
        int max = 0;
        Location maximumCost = null;
        for (Location location: result) {
            if (location.maximumMoney >= max && location.maximumMoney <= money) {
                max = location.maximumMoney;
                maximumCost = location;
            }
        }
        //TODO: Add climate filter
        if (maximumCost == null) {
            if (result == null || result.isEmpty()) {
                renderJSON(new LocationDto(new Location(-1L)));
            } else {
                renderJSON(new LocationDto(result.get(0)));
            }
        }
        renderJSON(new LocationDto(maximumCost));
    }
}