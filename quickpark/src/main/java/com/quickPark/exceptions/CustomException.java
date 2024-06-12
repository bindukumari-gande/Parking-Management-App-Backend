package com.quickPark.exceptions;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	private String message;

	public CustomException(String message) {
		super();
		this.message = message;
	}
	
}
