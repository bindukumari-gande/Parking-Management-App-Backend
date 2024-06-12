package com.quickPark.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int blockId;
	private String blockName;
	private String blockType;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	//@JsonManagedReference
	private List<Slot> slots;

	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "mallId")
	@JsonIgnore
	ShoppingMall mall;
	
	//private String slots;

	// commented above code for now keeping single side relationship

}
