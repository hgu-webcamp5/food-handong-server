package com.webcamp5.foodhandongserver.repository;

import com.webcamp5.foodhandongserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
