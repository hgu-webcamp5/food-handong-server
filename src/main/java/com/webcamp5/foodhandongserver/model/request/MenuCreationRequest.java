package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;
import lombok.Getter;


@Data
public class MenuCreationRequest {
    private String name;
    private int price;
    private String imageUrl;
}
