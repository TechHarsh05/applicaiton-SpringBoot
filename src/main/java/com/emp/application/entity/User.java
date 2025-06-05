package com.emp.application.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 101, allocationSize = 1)
	@Id
	private long id;
	private String name;
	@Lob
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] userImage;
	private String email;
	private String password;
	private long phone;
	
	
	public User(String name, byte[] userImage, String email, String password, long phone) {
//		public User(String name, String email, String password, long phone) {
				super();
		this.name = name;
		this.userImage = userImage;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getUsername() {
		return this.email;
	}
	
	
}
