package me.hongmo.querydsl.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    private String locationName;

    @OneToMany(mappedBy = "location")
    List<Building> buildings = new ArrayList<>();
}
