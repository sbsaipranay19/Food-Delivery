package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Login;
import com.learning.entity.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.LoginRepository;
import com.learning.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository repository;

	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Login result = repository.save(login);
		if (result != null)
			return "success";
		return "fail";
	}

	@Override
	public String deleteCredentials(String email) {
		Optional<Login> register3 = repository.findById(email);
		if (register3.isEmpty())
			return "fail";
		else {
			repository.deleteById(email);
			return "success";
		}
	}

	@Override
	public Login getUserById(String email) throws IdNotFoundException {
		System.out.println(email);
		Optional<Login> register3 = repository.findById("sbsai48@gmail.com");
		if (register3.isEmpty()) {
			throw new IdNotFoundException("ID does not exists");
		} else
			return register3.get();
	}

}
