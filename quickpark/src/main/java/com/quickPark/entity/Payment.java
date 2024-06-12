package com.quickPark.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Payment {

	private int bookingId;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private int farePerHour = 5;
	private double totalPrice;
	private String message;
}
