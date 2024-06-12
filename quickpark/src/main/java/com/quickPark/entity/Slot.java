package com.quickPark.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int slotId;
	private int slotNumber;
	private String status;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "blockId")
	private Block block;

}
