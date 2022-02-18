package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	boolean existsById(Long i);

	boolean existsByFoodName(String foodName);
}
