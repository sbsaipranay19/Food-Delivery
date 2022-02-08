package com.learning.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.learning.utils.PasswordUtils;


@Configuration
public class Config {
	@Autowired
	Environment environment;
	@Bean
	public PasswordUtils passwordUtils() {
		return new PasswordUtils();
	}
}

