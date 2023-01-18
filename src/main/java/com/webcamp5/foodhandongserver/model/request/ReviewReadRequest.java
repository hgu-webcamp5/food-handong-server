package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class ReviewReadRequest {
    private Long id;
    private int restaurantId;
    private String review;
    private double rating;
    private String imageUrl;
    private String userId;
    private Timestamp createdTime;
    private Timestamp  modifiedTime;
    private boolean isDeleted;
    private String userName;
    private String profileUrl;
}
