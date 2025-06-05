package com.emp.application.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.emp.application.dao.Dao;
import com.emp.application.entity.User;

import jakarta.persistence.EntityManager;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private Dao dao;
	@Autowired
	private EntityManager entityManager;
	
//	public Product insertProductService(
//		
//		System.out.println(user.getId()+ user.getName()+ user.getEmail()+ user.getPassword()+ user.getPhone());
//		return null;
//	}

	public void insertUserService(User user, MultipartFile file) {
		 // Get file as byte array
       
		try {
			byte[] imageBytes = file.getBytes();
			// Process the file and save user (service layer handles it)
			dao.insertUserDao(user, imageBytes);
		} catch (IOException e) {
			System.out.println("Could Not Convert Into Byte " + e.getMessage());
		}	
	}
	
	public String deleteUserService(long id){
		User user = entityManager.find(User.class, id);
		if(user != null) {			
			dao.deleteUserDao(id);
			return "User Deleted !";
		}
		return "User Did Not Find";
		
	}
	
	
//	public User insertUserService(User user) {
//		 // Get file as byte array
//        
//		if(user!=null)
//			return dao.insertUserDao(user);	
//		else
//		return null;
//	}
	
	public List<User> getUserService(){
		
		return dao.getAllUsersDao();
		
	}
	
	public Optional<User> findByEmailService(String email){
		return dao.findByEmailDao(email);
	}



}
