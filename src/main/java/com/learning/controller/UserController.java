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
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.UserService;

@RestController

public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody Register register) {
		// Add new User Record also adds the values into login id is generated
		// automaticly
		try {
			Register result = userService.addUser(register);
			return ResponseEntity.status(201).body(result);

		} catch (AlreadyExistsException e) {
			// TODO Auto-generated catch block
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("message", "record already exists");

			return ResponseEntity.badRequest().body(hashMap);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		// returns a user by given id
		Register register = userService.getUserById(id);
//		return ResponseEntity.ok(register);
		HashMap<String, String> map = new HashMap<>();
		if (register != null)
			return ResponseEntity.ok(register);
		map.put("message", "Sorry User With" + id + "not found");
		return ResponseEntity.status(201).body(map);
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUserDetails() {
		// returns all users
		Optional<List<Register>> optional = userService.getAllUserDetails();
		if (optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping("/authenticate/{email}/{password}")
	public ResponseEntity<?> authenticateUser(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		// checks if the given mail and password are present in mysql login table
		String result = userService.authenticateUser(email, password);
		return ResponseEntity.status(201).body(result);

	}

	@PutMapping("/users/userID")
	public ResponseEntity<?> updateUser(@RequestBody Register register) {
		// updates the user
		Register result = userService.updateUser(register);
		if (result != null)
			return ResponseEntity.status(201).body(result);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Sorry User not found");
		return ResponseEntity.status(201).body(map);

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		// delete the user with given id
		String result;
		try {
			HashMap<String, String> map = new HashMap<>();
			result = userService.deleteUserById(id);
			map.put("message", result);
			return ResponseEntity.status(201).body(map);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Sorry User With" + id + "not found");
		return ResponseEntity.status(201).body(map);

	}
}