package com.webcamp5.foodhandongserver.controller;

import com.webcamp5.foodhandongserver.model.Like;
import com.webcamp5.foodhandongserver.model.Restaurant;
import com.webcamp5.foodhandongserver.model.request.LikedRestaurantRequest;
import com.webcamp5.foodhandongserver.model.request.RestaurantCreationRequest;
import com.webcamp5.foodhandongserver.model.request.ReviewReadRequest;
import com.webcamp5.foodhandongserver.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

//    @GetMapping("/restaurant")
//    public ResponseEntity readRestaurants() {
//
//        return ResponseEntity.ok(restaurantService.readRestaurants());
//    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> readRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.readRestaurant(restaurantId));
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantCreationRequest request) {
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }


    // 식당 좋아요 POST
    @PostMapping("/restaurant/like")
    public ResponseEntity<Like> likeRestaurant(@RequestBody LikedRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.likeRestaurant(request));
    }
    // 식당 좋아요 GET
    @GetMapping("/restaurant/like")
    public ResponseEntity readlike() {
        return ResponseEntity.ok(restaurantService.readlike());
    }

    //식당 좋아요 취소
    @PatchMapping("/restaurant/unlike")
    public ResponseEntity<Like> unlikeRestaurant(@RequestBody LikedRestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.unlikeRestaurant(request));
    }

    // 회원 ID로 해당 회원이 좋아한 식당 조회
    @GetMapping("/restaurant")
    public ResponseEntity readRestaurants(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return ResponseEntity.ok(restaurantService.readRestaurants());
        }
        return ResponseEntity.ok(restaurantService.readLikedRestaurant(userId));
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantCreationRequest request , @PathVariable Long restaurantId){
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, request));
    }

}
