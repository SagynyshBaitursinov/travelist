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
            if (location.maximumMoney <= money && location.climate == climate) {
                result.add(location);
            }
        }
        Location finalResult = null;
        int maxPoints = 0;
        for (Location location: result) {
            int points = 0;
            for (int i = 0; i < geo.length; i++) {
                if (location.geography.contains(geo[i])) {
                    points++;
                }
            }
            for (int i = 0; i < activities.length; i++) {
                if (location.activities.contains(activities[i])) {
                    points++;
                }
            }
            if (points > maxPoints) {
                finalResult = location;
                maxPoints = points;
            }
        }
        if (finalResult == null) {
            renderJSON(new LocationDto(new Location(-1L)));
        } else {
            renderJSON(new LocationDto(finalResult));
        }
    }
}