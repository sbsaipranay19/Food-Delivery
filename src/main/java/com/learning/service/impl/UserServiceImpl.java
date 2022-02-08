package com.learning.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Login;
import com.learning.entity.Register;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.LoginRepository;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private LoginServiceImpl service;

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		// make exception for the next line
		if (userRepository.existsByEmail(register.getEmail())) {
			throw new AlreadyExistsException("User already exists");
		}
		Register register2 = userRepository.save(register);
		if (register2 != null) {
			Login login = new Login(register2.getEmail(),register2.getPassword(),register2);
			String res = service.addCredentials(login);
			if(res.equals("success")) {
				return register2;
			}else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public Register getUserById(int regId){
		Optional<Register> register3 = userRepository.findById(regId);
			if(register3!=null)
			return register3.get();
			return null;
	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		List<Register> list = userRepository.findAll();
		Register[] result = new Register[list.size()];

		return list.toArray(result);
	}

	@Override
	public String deleteUserById(int regId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Register optional;
		try {
			optional = this.getUserById(regId);
			if(optional==null) {
				throw new IdNotFoundException("Record Not Found");
			}else {
				userRepository.deleteById(regId);
				return "User Deleted Successfully";
			}
		}catch (IdNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public Optional<List<Register>> getAllUserDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public Register updateUser(Register register) {
		// TODO Auto-generated method stub
		
		Register register2 = userRepository.save(register);
		if (register2 != null) {
			Login login = new Login(register2.getEmail(),register2.getPassword(),register2);
			String res = service.addCredentials(login);
			if(res.equals("success")) {
				return register2;
			}else {
				return null;
			}
		} else {
			return null;
		}
	}

	
	@Override
	public String authenticateUser(String email, String password) {
		// TODO Auto-generated method stub
		Login login;
		try {
			login = service.getUserById(email);
			if(login.getPassword().equals(password))
				return "success";
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
		
	}
	

}
