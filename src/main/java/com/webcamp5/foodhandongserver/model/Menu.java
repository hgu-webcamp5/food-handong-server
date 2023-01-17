package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

//    private String createdTime;
//    private String modifiedTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Menu() {}


//    @Builder
//    public Menu(String name , int price , String imageUrl , Restaurant restaurant){
//        this.name = name;
//        this.price = price;
//        this.imageUrl = imageUrl;
//        this.restaurant = restaurant;
//    }
//
//
//
//    public static Menu createMenu(String name,int price , String imageUrl  ,Restaurant restaurant){
//        return Menu.builder()
//                .name(name)
//                  .price(price)
//                  .imageUrl(imageUrl)
//                .restaurant(restaurant)
//                .build();
//    }


}
