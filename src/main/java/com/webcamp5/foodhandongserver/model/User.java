package com.webcamp5.foodhandongserver.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String profileUrl;
    private String nickname;
    private String userId;
    private String password;
    private int loginCount;
}
