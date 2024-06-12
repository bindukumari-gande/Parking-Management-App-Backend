package com.quickPark.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@JsonFormat(pattern = "dd:MM:yyyy")
	private LocalDate slotDate;

	@JsonFormat(pattern = "hh:mm:ss")
	private LocalTime startTime;

	@JsonFormat(pattern = "hh:mm:ss")
	private LocalTime endTime;

	private String status;

	private String payment;

	private float totalprice;
	
	private String vehicleNo;
	
	private int vehicleType; 
	
	@OneToOne
	@JoinColumn(name = "mallId")
	ShoppingMall mall;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	Customer customer;

}
