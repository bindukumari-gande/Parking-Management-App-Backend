package com.quickPark.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingMall {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int mallId;

	private String mallName;

	private String mallEmail;

	private long mallMobileNo;

	private String address;
	
	private String password;

	/*
	 * @JsonFormat(pattern = "HH:mm:ss")
	 * 
	 * @JsonIgnore private LocalTime startTime;
	 * 
	 * @JsonFormat(pattern = "HH:mm:ss")
	 * 
	 * @JsonIgnore private LocalTime endTime;
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loginId")
	@JsonIgnore
	Login login;

	@OneToMany(mappedBy = "mall")
//	@JoinColumn(name = "blockId")
	@JsonIgnore
	private List<Block> blocks;
	
	/*
	 * @OneToOne
	 * 
	 * @JsonIgnore MyBooking booking;
	 */



}
