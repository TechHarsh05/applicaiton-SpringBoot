package com.emp.application.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dto {
	
	@Nonnull
	private long id;

	@Nonnull
	private String name;

	@Lob
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] userImage;

	@Nonnull
	private String email;

//	@Nonnull
	private String password;

	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

//	@Nonnull
	public Long phone;
}

