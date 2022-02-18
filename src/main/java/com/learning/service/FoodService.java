package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Food;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.entity.EFoodType;

public interface FoodService {
	
	public Food addFood(Food food) throws AlreadyExistsException;
	
	public Food getFoodById(Long foodId) throws IdNotFoundException;
	
//	public Optional<List<Food>> getFoodByType(String foodType);

	public Optional<List<Food>> getAllFoods();

	public String deleteFoodById(Long foodId) throws IdNotFoundException;
	
	public Food updateFood(Food food);

	

}
