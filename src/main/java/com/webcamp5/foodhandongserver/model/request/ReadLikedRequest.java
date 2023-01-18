package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class ReadLikedRequest {
    private Long id;
    private Boolean isCancelled;
    private Long userId;
    private Long restaurantId;
}
