package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.*;
import com.webcamp5.foodhandongserver.model.request.*;
import com.webcamp5.foodhandongserver.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final LikeRepository likeRepository;

    private final ReviewRepository reviewRepository;

    public Restaurant readRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        double sum = 0;
        double average = 0;
        int likeSum = 0;

        List<Review> reviewList = reviewRepository.findAllByRestaurantId(restaurant.get().getId());
        restaurant.get().setComment(reviewList.size());

        for(Review review : reviewList){
            sum += review.getRating();
        }

        if(reviewList.size() != 0) average =  sum / reviewList.size();
        average= (double)Math.round(average*10)/10;
        restaurant.get().setRate(average);

        List<Like> likeList = likeRepository.findAllByRestaurantId(restaurant.get().getId());

        for(Like like : likeList){
            if(like.getIsCancelled() == true) continue;
            likeSum += 1;
        }

        restaurant.get().setHeart(likeSum);

        if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new EntityNotFoundException("Cant find any restaurant under given ID");
    }
    public List<Restaurant> readRestaurants() {

        List<Restaurant> restList = restaurantRepository.findAll();

        for(Restaurant restaurant : restList){
            double sum = 0;
            double average = 0;
            int likeSum = 0;

            List<Review> reviewList = reviewRepository.findAllByRestaurantId(restaurant.getId());
            restaurant.setComment(reviewList.size());

            for(Review review : reviewList){
                sum += review.getRating();
            }

            if(reviewList.size() != 0) average =  sum / reviewList.size();
            average= (double)Math.round(average*10)/10;
            restaurant.setRate(average);

            List<Like> likeList = likeRepository.findAllByRestaurantId(restaurant.getId());

            for(Like like : likeList){
                if(like.getIsCancelled() == true) continue;
                likeSum += 1;
            }

            restaurant.setHeart(likeSum);

        }

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

        List<Menu> menus = restaurant.getMenus();
        for(Menu menuDTO : menus){
            menuDTO.setRestaurant(restaurantToCreate);
        }

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
        List<Like> likedRestaurants = likeRepository.findAllByRestaurantId(like.getRestaurantId());

        Collections.reverse(likedRestaurants);

        for(Like likeRestaurant : likedRestaurants){
            if (likeRestaurant.getUserId().equals(like.getUserId())) {
                Like updateLike = likeRepository.findById(likeRestaurant.getId()).get();
                updateLike.setIsCancelled(true);
                return likeRepository.save(likeRestaurant);
            }
        }

        throw new EntityNotFoundException("Like not present in the database");
    }

    // 회원 ID로 해당 회원이 좋아한 식당 조회
    public List<LikedRestaurantResponse> readLikedRestaurant(String userId) {
        List<Like> restaurant = likeRepository.findAllByUserId(userId);
        if(!restaurant.isEmpty()) {
            List<LikedRestaurantResponse> result = new ArrayList<>();
            restaurant.forEach(review -> {
                Restaurant obj = readRestaurant(review.getRestaurantId());
                LikedRestaurantResponse obj2 = new LikedRestaurantResponse();
                BeanUtils.copyProperties(obj, obj2);
                obj2.setCancelled(review.getIsCancelled());
                result.add(obj2);
            });
            return result;
        }
        throw new EntityNotFoundException("Cant find any restaurant under given ID");
    }
}
