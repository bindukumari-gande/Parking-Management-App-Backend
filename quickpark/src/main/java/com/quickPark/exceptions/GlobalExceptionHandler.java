package com.quickPark.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotPresentException.class)
	public ResponseEntity<ErrorDetails> handleCustomerNotPresentException(CustomerNotPresentException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDetails> handleCustomException(CustomException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MallNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleMallNotFoundException(MallNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchBlockExistsException.class)
	public ResponseEntity<ErrorDetails> handleNoSuchBlockExistsException(NoSuchBlockExistsException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SlotNotAvailableException.class)
	public ResponseEntity<ErrorDetails> handleSlotNotAvailableException(SlotNotAvailableException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<ErrorDetails> handleEmptyFieldException(EmptyFieldException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBookingNotFoundException(BookingNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
