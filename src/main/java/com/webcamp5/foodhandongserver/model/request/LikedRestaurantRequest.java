package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class LikedRestaurantRequest {
    private Long userId;
    private Long restaurantId;
}
