package com.emp.application.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emp.application.dao.Dto;
import com.emp.application.entity.User;
import com.emp.application.jwtHelper.JwtHelper;
import com.emp.application.model.jwtRequest;
import com.emp.application.model.jwtResponse;
import com.emp.application.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController @RequestMapping("/auth") @CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired private UserDetailsService userDetailsService;
    @Autowired private AuthenticationManager manager;
    @Autowired private Service service;
    @Autowired private JwtHelper helper;
    @Autowired private ModelMapper mapper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    

    @PostMapping("/login")
    public ResponseEntity<jwtResponse> login(@RequestBody jwtRequest request) {
    	logger.info("User login successful.");
    	logger.warn("Invalid login attempt.");


        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        jwtResponse response = jwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
//        jwtResponse response =  jwtResponse
//                response.setJwtToken(token);
//        		response.setUser(this.mapper.map((User) userDetails, Dto.class));
//                .username(userDetails.getUsername())

//        		.user(this.mapper.map((User) userDetails, Dto.class))
//                .build();
        		return new ResponseEntity<jwtResponse>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new 
        		UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
            

        } catch (BadCredentialsException e) {
        	logger.error("Exception occurred: ", e);
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

//    /*
	@PostMapping("/signup")
	public ResponseEntity<String> insertProductsController(
            @RequestPart("user") String userJson, // Using String to receive JSON data
            @RequestPart("file") MultipartFile file){
		try {
        // Convert the JSON string into a User object
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);
        System.out.println(userJson);
        System.out.println(file);

//             File size validation (optional)
            long maxFileSize = 5 * 1024 * 1024;  // 5 MB limit
            if (file.getSize() > maxFileSize) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("File size exceeds the maximum limit of 5 MB");
            }else {
            	
            service.insertUserService(user, file);
            return ResponseEntity.ok("Image uploaded successfully");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }

    }
//    */
    
//    @PostMapping("/signup")
//    public ResponseEntity<User> insertUserController(@RequestBody User user){
//    	if(user!=null) {
//    		User me= service.insertUserService(user);
//    	return ResponseEntity.ok(me);
//    	}else {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//    	}
//    }
}
