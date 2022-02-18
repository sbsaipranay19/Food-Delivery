package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.EFoodType;
import com.learning.entity.Food;
import com.learning.entity.FoodType;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.AddFoodRequest;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.FoodRepository;
import com.learning.repo.FoodTypeRepository;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController1 {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	FoodService foodService;

	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@PostMapping("/addFood")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addFood(@Valid @RequestBody AddFoodRequest addFoodRequest) {

		if (foodRepository.existsByFoodName(addFoodRequest.getFoodName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Food name already taken!"));
		}
		Food foods = new Food(addFoodRequest.getFoodName(), addFoodRequest.getFoodCost(), addFoodRequest.getFoodPic());

		Set<String> s1 = addFoodRequest.getFoodTypes();
		Set<FoodType> roles = new HashSet<>();
		if (s1 == null) {
			FoodType foodType = foodTypeRepository.findByFoodType(EFoodType.Chinese)
					.orElseThrow(() -> new RuntimeException("Error:role not found"));
			roles.add(foodType);
		}

		else {
			s1.forEach(e -> {
				switch (e) {
				case "Chinese":
					FoodType chinese = foodTypeRepository.findByFoodType(EFoodType.Chinese)
							.orElseThrow(() -> new RuntimeException("Error:role not found"));
					roles.add(chinese);
					break;
				case "Indian":
					FoodType indian = foodTypeRepository.findByFoodType(EFoodType.Indian)
							.orElseThrow(() -> new RuntimeException("Error:role not found"));
					roles.add(indian);
					break;
				case "Mexican":
					FoodType mexican = foodTypeRepository.findByFoodType(EFoodType.Mexican)
							.orElseThrow(() -> new RuntimeException("Error:role not found"));
					roles.add(mexican);
					break;

				default:

				}
			});
		}

		foods.setFoodtypes(roles);
		foodRepository.save(foods);
		return ResponseEntity.status(201).body(new MessageResponse("Food inserted successfully"));
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllMovies() {
		List<Food> list = foodRepository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No record found"));
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{foodId}")
	public ResponseEntity<?> getFood(@PathVariable Long foodId) throws IdNotFoundException {
		// takes the given id and searches the food with that id and returns it
		Food food = foodService.getFoodById(foodId);
		if (food != null)
			return ResponseEntity.ok(food);
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("message", "Sorry Food Not Found");
		return ResponseEntity.badRequest().body(hashMap);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/foodID")
	public ResponseEntity<?> updateFood(@RequestBody Food food) {
		// update the food with new vales given
		Food result = foodService.updateFood(food);
		if (food != null)
			return ResponseEntity.ok(result);
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("message", "Sorry Food Not Found");
		return ResponseEntity.badRequest().body(hashMap);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{foodId}")
	public ResponseEntity<?> deleteFood(@PathVariable("foodId") Long foodId) {
		// Delete the food item
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

}
