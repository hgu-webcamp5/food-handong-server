package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long id);
}
