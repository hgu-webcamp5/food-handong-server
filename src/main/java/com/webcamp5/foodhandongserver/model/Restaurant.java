package com.webcamp5.foodhandongserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

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

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @JsonIgnore(value = false)
    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();


//    @JsonIgnore(value = false)
//    @OneToMany(mappedBy = "restaurant",
//            cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

    private double rate;
    private int comment;
    private int heart;
//    @JsonIgnore(value = false)
//    @OneToMany(mappedBy = "restaurant",
//            cascade = CascadeType.ALL)
//    private Like likes;


//    private int stars;
//    private int likes;
//    private int comment;




    public Restaurant(){}
//    @Builder
//    public Restaurant(String contact ,String dong , String imageUrl , double latitude , double longitude ,
//                      String location , String name , String officialName , String openingHours )
//    {
//        this.contact =contact;
//        this.dong = dong;
//        this.imageUrl = imageUrl;
//        this.latitude = latitude;
//        this.longitude =longitude;
//        this.location =location;
//        this.name =name;
//        this.officialName = officialName;
//        this.openingHours = openingHours;
//    }
//    public static Restaurant createRestaurant(String contact ,String dong , String imageUrl , double latitude , double longitude ,
//                                              String location , String name , String officialName , String openingHours)
//    {
//        return Restaurant.builder()
//                .contact(contact)
//                .dong(dong)
//                .imageUrl(imageUrl)
//                .latitude(latitude)
//                .longitude(longitude)
//                .location(location)
//                .name(name)
//                .officialName(officialName)
//                .openingHours(openingHours)
//                .build();
//    }
//    public void putMenu(Menu menu){
//        this.menus.add(menu);
//    }

}

