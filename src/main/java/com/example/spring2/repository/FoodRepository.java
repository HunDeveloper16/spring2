package com.example.spring2.repository;

import com.example.spring2.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByRestaurantIdAndName(Long RestaurantId, String FoodName);
    List<Food> findByRestaurantId(Long RestaurantId);
}
