package com.webcamp5.foodhandongserver.controller;

import com.webcamp5.foodhandongserver.model.User;
import com.webcamp5.foodhandongserver.model.request.UserCreationRequest;
import com.webcamp5.foodhandongserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity readUsers() {
        return ResponseEntity.ok(userService.readUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> readUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.readUserByUserId(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> updateUser (@RequestBody UserCreationRequest request, @PathVariable Long userId){
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }
}
