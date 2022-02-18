package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.EFoodType;
import com.learning.entity.FoodType;
import com.learning.entity.Role;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, String> {
	Optional<FoodType> findByFoodType(EFoodType foodType);
}
