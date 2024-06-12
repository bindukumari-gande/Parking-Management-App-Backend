package com.quickPark.exceptions;

import lombok.Data;

@Data 
public class CustomerNotPresentException extends RuntimeException {

	public CustomerNotPresentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
