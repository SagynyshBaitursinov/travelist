package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by sagynysh on 4/2/17.
 */
@Entity
@Table(name = "locations")
public class Location extends GenericModel {

    public Location() {

    }

    public Location(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    public Long id;

    @Column(name = "name_")
    public String name;

    @Column(name = "geography_")
    public String geography;

    @Column(name = "activities_")
    public String activities;

    @Column(name = "climate_")
    public Integer climate;

    @Column(name = "money_")
    public Integer maximumMoney;
}
