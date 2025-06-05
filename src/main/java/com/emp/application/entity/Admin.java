package com.emp.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Admin {
	@Id
	private String username;
	private String password;
}
