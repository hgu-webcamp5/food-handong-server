package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
