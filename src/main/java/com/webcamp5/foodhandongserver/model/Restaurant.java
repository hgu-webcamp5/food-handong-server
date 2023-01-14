package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long categoryId;
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
    @JsonManagedReference
    private Category category;
}

