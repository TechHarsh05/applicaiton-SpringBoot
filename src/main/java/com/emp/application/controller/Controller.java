package com.emp.application.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.application.entity.User;
import com.emp.application.service.Service;

@RequestMapping("/dashboard")
@RestController
@CrossOrigin("http://localhost:5173")
public class Controller {
	
	@Autowired
	Service service;

	@GetMapping("/current-user")
	public String CurrentUserController(Principal principal) {
		return principal.getName();		
	}
	
	@PostMapping("/delete-user")
	public String deleteUserController(long id) {
		return service.deleteUserService(id);
	}
	
	@GetMapping("/get-users")
	public ResponseEntity<List<User>> getUsersController() {
		
		List<User> users = service.getUserService();
		
		if(users != null && !users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
}
