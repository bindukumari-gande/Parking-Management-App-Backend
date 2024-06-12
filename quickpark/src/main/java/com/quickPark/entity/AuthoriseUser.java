package com.quickPark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthoriseUser {

	private String email;
	private String password;
	private String role;
	
}
