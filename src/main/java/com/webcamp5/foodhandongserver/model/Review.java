package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int restaurantId;
    private String review;
    private double rating;
    private int userId;
//    private java.sql.Timestamp createdTime;
//    private java.sql.Timestamp  modifiedTime;
    private short isDeleted;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "restaurant_id")
//    private Restaurant restaurant;

}
