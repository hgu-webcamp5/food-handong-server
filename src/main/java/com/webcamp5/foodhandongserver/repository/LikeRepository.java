package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.Like;
import com.webcamp5.foodhandongserver.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByRestaurantId(Long restaurantId);
}
