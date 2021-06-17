package com.bezkoder.springjwt.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;

import com.bezkoder.springjwt.security.services.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")

public class editProfile {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/usuarios")
	public List<User> index(){
		return userService.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public User show(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable Long id) {
		User userActual = userService.findById(id);

		userActual.setTelefono(user.getTelefono());
		userActual.setApellidos(user.getApellidos());
		userActual.setNombre(user.getNombre());
		userActual.setDNI(user.getDNI());
		userActual.setFacturacion(user.getFacturacion());
		userActual.setCod_postal(user.getCod_postal());
		userActual.setDate(user.getDate());
		System.out.println(user.getDate());
		return userService.save(userActual);
	}
	
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

}
