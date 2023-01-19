package com.webcamp5.foodhandongserver.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webcamp5.foodhandongserver.model.Category;
import com.webcamp5.foodhandongserver.model.Menu;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LikedRestaurantResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contact;
    private String dong;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String location;
    private String name;
    private String officialName;
    private String openingHours;
    private boolean isCancelled;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @JsonIgnore(value = false)
    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    private double rate;
    private int comment;
    private int heart;

}
