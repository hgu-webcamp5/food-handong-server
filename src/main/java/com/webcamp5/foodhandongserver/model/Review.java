package com.webcamp5.foodhandongserver.model;

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

}
