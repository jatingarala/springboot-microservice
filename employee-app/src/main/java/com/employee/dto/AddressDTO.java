package com.employee.dto;

import javax.persistence.Column;

import lombok.ToString;

@ToString
public class AddressDTO {
	
	private int id;
	

	private String housename;


	private String street;


	private Integer pincode;


	public AddressDTO(int id, String housename, String street, Integer pincode) {
		super();
		this.id = id;
		this.housename = housename;
		this.street = street;
		this.pincode = pincode;
	}


	public AddressDTO() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getHousename() {
		return housename;
	}


	public void setHousename(String housename) {
		this.housename = housename;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public Integer getPincode() {
		return pincode;
	}


	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	

}
