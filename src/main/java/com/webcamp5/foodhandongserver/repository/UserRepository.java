package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByPassword(String password);
}
