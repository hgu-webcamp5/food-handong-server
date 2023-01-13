package com.webcamp5.foodhandongserver.controller;

import com.webcamp5.foodhandongserver.model.Restaurant;
import com.webcamp5.foodhandongserver.model.request.RestaurantCreationRequest;
import com.webcamp5.foodhandongserver.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;


@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurant")
    public ResponseEntity readRestaurants() {

        return ResponseEntity.ok(restaurantService.readRestaurants());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> readRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.readRestaurant(restaurantId));
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantCreationRequest request) {
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok().build();
    }
}
