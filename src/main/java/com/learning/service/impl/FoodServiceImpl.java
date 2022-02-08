package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.EFoodType;
import com.learning.entity.Food;
import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.FoodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Food addFood(Food food) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		if (foodRepository.existsById(food.getFoodId())) {
			throw new AlreadyExistsException("User already exists");
		}
		Food food1 = foodRepository.save(food);
		if (food1 != null) {
				return food;
			
		} else {
			return food;
		}
	}

	@Override
	public Food getFoodById(int foodId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Food> food1 = foodRepository.findById(foodId);
		if (food1.isEmpty()) {
			throw new IdNotFoundException("ID does not exists");
		} else
			return food1.get();
	}

	@Override
	public Optional<List<Food>> getFoodByType(EFoodType foodType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Food>> getAllFoods() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepository.findAll());
	}

	@Override
	public String deleteFoodById(int foodId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Food optional;
		try {
			optional = this.getFoodById(foodId);
			
				foodRepository.deleteById(foodId);
				return "Food Item Deleted";
			
		}catch (IdNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Food updateFood(Food food) {
		// TODO Auto-generated method stub
		Food food1 = foodRepository.save(food);
		if (food1 != null) {
			return food;
		
	} else {
		return null;
	}
	}

}
