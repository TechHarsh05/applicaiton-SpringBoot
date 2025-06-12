package com.emp.application.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.application.dao.Dto;
import com.emp.application.entity.User;
import com.emp.application.service.Service;

import jakarta.validation.Valid;

@RequestMapping("/dashboard")
@RestController
public class Controller {
	
	@Autowired
	Service service;

	@GetMapping("/current-user")
	public String CurrentUserController(Principal principal) {
		return principal.getName();		
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<?> deleteUserController(@RequestBody Dto request) {
	    Long id = request.getId();
	    if (id != null && id != 0) {
	        System.out.println("Deleting user with ID: " + id);
	        service.deleteUserService(id);
	        return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.badRequest().build();
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
	
	@PostMapping("/update-user")
	public ResponseEntity<?> updateUserController(@Valid @RequestBody Dto dto) {
		System.out.println("DTO received: " + dto); // log actual contents
	    System.out.println("Updating user: " + dto.getEmail());
	    service.updateUserService(dto);
	    return ResponseEntity.ok("User updated successfully");
	}

	
	
}
