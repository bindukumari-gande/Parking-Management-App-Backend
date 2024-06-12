package com.quickPark.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loginId;
	private String email;
	private String password;
	private String role;



}
