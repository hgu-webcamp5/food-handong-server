package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contact;
    private String dong;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String location;
    private String name;
    private String officialName;
    private String openingHours;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @JsonIgnore(value = false)
    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    private double rate;
    private int comment;
    private int heart;

    public Restaurant(){}

}

