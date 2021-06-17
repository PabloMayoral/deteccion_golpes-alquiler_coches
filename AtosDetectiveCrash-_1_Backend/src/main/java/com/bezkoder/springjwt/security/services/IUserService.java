package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bezkoder.springjwt.models.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User save(User user);
	
	public void delete(Long id);

}
