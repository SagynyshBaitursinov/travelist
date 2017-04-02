package dto;

import models.Location;

/**
 * Created by sagynysh on 4/2/17.
 */
public class LocationDto {

    public Long id;

    public String name;

    public LocationDto(Location location) {
        this.id = location.id;
        this.name = location.name;
    }
}
