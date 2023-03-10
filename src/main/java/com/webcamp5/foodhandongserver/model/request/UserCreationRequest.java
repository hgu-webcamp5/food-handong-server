package com.webcamp5.foodhandongserver.model.request;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String name;
    private String email;
    private String profileUrl;
    private String nickname;
    private String userId;
    private String password;
    private int loginCount;
}
