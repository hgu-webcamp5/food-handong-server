package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.Category;
import com.webcamp5.foodhandongserver.model.Like;
import com.webcamp5.foodhandongserver.model.Restaurant;
import com.webcamp5.foodhandongserver.model.request.*;
import com.webcamp5.foodhandongserver.repository.CategoryRepository;
import com.webcamp5.foodhandongserver.model.request.RestaurantCreationRequest;
import com.webcamp5.foodhandongserver.repository.LikeRepository;
import com.webcamp5.foodhandongserver.repository.RestaurantRepository;
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
    private final CategoryRepository categoryRepository;
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

        Optional<Category> category = categoryRepository.findById(restaurant.getCategoryId());
        if(!category.isPresent()){
            throw new EntityNotFoundException(
                    "Category Not Found");
        }

        Restaurant restaurantToCreate = new Restaurant();
        BeanUtils.copyProperties(restaurant, restaurantToCreate);
        restaurantToCreate.setCategory(category.get());
        return restaurantRepository.save(restaurantToCreate);

    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant updateRestaurant(Long id,RestaurantCreationRequest request){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(!optionalRestaurant.isPresent()){
            throw new EntityNotFoundException(
                    "restaurant not present in the database");
        }

        Restaurant restaurant = optionalRestaurant.get();

//        restaurant.setCategoryId(request.getCategoryId());
        restaurant.setContact(request.getContact());
        restaurant.setDong(request.getDong());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurant.setLocation(request.getLocation());
        restaurant.setName(request.getName());
        restaurant.setOfficialName(request.getOfficialName());
        restaurant.setOpeningHours(request.getOpeningHours());

        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        restaurant.setCategory(category.get());

        return restaurantRepository.save(restaurant);

    }
    // 식당 좋아요 POST
    public Like likeRestaurant(LikedRestaurantRequest like) {
        Like likedRestaurant = new Like();
        BeanUtils.copyProperties(like, likedRestaurant);
        likedRestaurant.setIsCancelled(false);
        return likeRepository.save(likedRestaurant);
    }
    // 식당 좋아요 GET
    public List<Like> readlike() {
        return likeRepository.findAll();
    }

    //식당 좋아요 취소
    public Like unlikeRestaurant(LikedRestaurantRequest like) {
        Like likedRestaurant = new Like();
        BeanUtils.copyProperties(like, likedRestaurant);
        likedRestaurant.setIsCancelled(true);
        return likeRepository.save(likedRestaurant);
    }

    // 회원 ID로 해당 회원이 좋아한 식당 조회
    public List<ReadLikedRequest> readLikedRestaurant(Long userId) {
        List<Like> restaurant = likeRepository.findAllByUserId(userId);
        if(!restaurant.isEmpty()) {
            List<ReadLikedRequest> result = new ArrayList<>();
            restaurant.forEach(review -> {
                ReadLikedRequest reviewObj = new ReadLikedRequest();
                BeanUtils.copyProperties(review,reviewObj);

                result.add(reviewObj);
            });
            return result;
        }
        throw new EntityNotFoundException("Cant find any restaurant under given ID");
    }
}
