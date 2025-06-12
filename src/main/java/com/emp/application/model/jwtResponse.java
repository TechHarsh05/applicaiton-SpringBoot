package com.emp.application.model;

import com.emp.application.dao.Dto;

//import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class jwtResponse {

	private String jwtToken;
//	private String username;
	private Dto user;
}
