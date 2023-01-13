package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class ReviewCreationRequest {
    private int restaurantId;
    private String review;
    private double rating;
    private int userId;
//    private java.sql.Timestamp createdTime;
//    private java.sql.Timestamp  modifiedTime;
    private short isDeleted;

}
