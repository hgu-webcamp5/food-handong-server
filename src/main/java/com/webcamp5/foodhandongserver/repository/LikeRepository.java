package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
