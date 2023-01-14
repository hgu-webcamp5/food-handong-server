package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class RestaurantCreationRequest {
    private Long categoryId;
    private String contact;
    private String dong;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String location;
    private String name;
    private String officialName;
    private String openingHours;
}
