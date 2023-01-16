package com.webcamp5.foodhandongserver.model.request;

import com.webcamp5.foodhandongserver.model.Menu;
import com.webcamp5.foodhandongserver.model.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private List<Menu> menus= new ArrayList<>();;

}
