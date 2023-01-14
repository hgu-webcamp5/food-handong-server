package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.*;
import com.webcamp5.foodhandongserver.model.request.LikedRestaurantRequest;
import com.webcamp5.foodhandongserver.model.request.RestaurantCreationRequest;
import com.webcamp5.foodhandongserver.repository.LikeRepository;
import com.webcamp5.foodhandongserver.repository.RestaurantRepository;
import com.webcamp5.foodhandongserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final LikeRepository likeRepository;

    public Restaurant readRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new EntityNotFoundException("Cant find any restaurant under given ID");
    }

    public List<Restaurant> readRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(RestaurantCreationRequest restaurant) {

        Restaurant restaurantToCreate = new Restaurant();
        BeanUtils.copyProperties(restaurant, restaurantToCreate);
        return restaurantRepository.save(restaurantToCreate);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Like likeRestaurant(LikedRestaurantRequest like) {
        Like likedRestaurant = new Like();
        BeanUtils.copyProperties(like, likedRestaurant);
        likedRestaurant.setIsCancelled(false);
        return likeRepository.save(likedRestaurant);
    }
    public List<Like> readlike() {
        return likeRepository.findAll();
    }

    public Like unlikeRestaurant(LikedRestaurantRequest like) {
        Like likedRestaurant = new Like();
        BeanUtils.copyProperties(like, likedRestaurant);
        likedRestaurant.setIsCancelled(true);
        return likeRepository.save(likedRestaurant);
    }

}
