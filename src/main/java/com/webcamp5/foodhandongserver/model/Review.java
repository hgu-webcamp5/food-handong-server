package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long restaurantId;
    private String review;
    private double rating;
    private String imageUrl;
    private Long userId;
    private Timestamp createdTime;
    private Timestamp  modifiedTime;
    private boolean isDeleted;
}


