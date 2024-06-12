package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BookingNotFoundException extends RuntimeException {

	private String message;
	
}
