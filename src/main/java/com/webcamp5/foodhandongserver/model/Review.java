package com.webcamp5.foodhandongserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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
    private Timestamp createdTime;
    private Timestamp  modifiedTime;
    private short isDeleted;

}
