package com.learning.service;

import com.learning.entity.Login;
import com.learning.exception.IdNotFoundException;

public interface LoginService {

	public String addCredentials(Login login);

	public String deleteCredentials(String email);
	public Login getUserById(String email) throws IdNotFoundException;

}
