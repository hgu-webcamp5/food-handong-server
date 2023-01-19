package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class ReviewCreationRequest {
    private Long restaurantId;
    private String review;
    private double rating;
    private String userId;
    private String imageUrl;
//    private java.sql.Timestamp createdTime;
//    private java.sql.Timestamp  modifiedTime;

}
