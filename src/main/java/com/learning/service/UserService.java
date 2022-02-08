package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;

public interface UserService {

	public Register addUser(Register register) throws AlreadyExistsException;

	public Register getUserById(int regId) throws IdNotFoundException;

	public Register[] getAllUsers();

	public String deleteUserById(int regId) throws IdNotFoundException;

	public Optional<List<Register>> getAllUserDetails();

	public Register updateUser(Register register);
	
	public String authenticateUser(String email,String password);

}
