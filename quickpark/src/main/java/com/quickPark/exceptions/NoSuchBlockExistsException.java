package com.quickPark.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class NoSuchBlockExistsException extends RuntimeException {

	private String message;
}
