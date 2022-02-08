package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.entity.Food;
import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;

public class FoodController {
	@Autowired
	FoodService foodService;

	@PostMapping("/food")
	public ResponseEntity<?> addUser(@RequestBody Food food) {
		try {
			Food result = foodService.addFood(food);
			return ResponseEntity.status(201).body(result);

		} catch (AlreadyExistsException e) {
			// TODO Auto-generated catch block
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("message", "record already exists");

			return ResponseEntity.badRequest().body(hashMap);
		}
	}

	@GetMapping("/food/{foodId}")
	public ResponseEntity<?> getFood(@PathVariable int foodId) throws IdNotFoundException {
		Food food = foodService.getFoodById(foodId);
		if (food != null)
			return ResponseEntity.ok(food);
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("message", "Sorry Food Not Found");
		return ResponseEntity.badRequest().body(hashMap);
	}

	@PutMapping("/food/foodID")
	public ResponseEntity<?> updateUser(@RequestBody Food food) {

		Food result = foodService.updateFood(food);
		if (food != null)
			return ResponseEntity.ok(result);
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("message", "Sorry Food Not Found");
		return ResponseEntity.badRequest().body(hashMap);

	}

	@DeleteMapping("/food/{foodId}")
	public ResponseEntity<?> deleteUser(@PathVariable("foodId") int foodId) {

		String result;

		HashMap<String, String> map = new HashMap<>();
		try {
			result = foodService.deleteFoodById(foodId);
			map.put("message", result);
			return ResponseEntity.status(201).body(map);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("message", "Sorry Food Item Not Found");
		return ResponseEntity.status(201).body(map);

	}
	
	@GetMapping("/food")
	public ResponseEntity<?> getAllUserDetails() {
		Optional<List<Food>> optional = foodService.getAllFoods();
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
}
