package com.webcamp5.foodhandongserver.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String createdTime;
    private String modifiedTime;

    @JsonBackReference
    @OneToMany(mappedBy = "category",
                fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;
}
