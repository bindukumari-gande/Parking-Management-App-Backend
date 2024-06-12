package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class MallNotFoundException extends RuntimeException {

	private String message;
	
	

}
