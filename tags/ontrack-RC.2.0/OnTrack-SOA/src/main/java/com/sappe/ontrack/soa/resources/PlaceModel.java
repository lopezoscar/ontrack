package com.sappe.ontrack.soa.resources;

public class PlaceModel {
	
	private String precint;
	
	private String name;
	
	private String address;
	
	private String postalCode;
	
	private String number;
	

	public String getPrecint() {
		return precint;
	}

	public void setPrecint(String precint) {
		this.precint = precint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PlaceModel [precint=" + precint + ", name=" + name
				+ ", address=" + address + ", postalCode=" + postalCode
				+ ", number=" + number + "]";
	}
	
	
	
	

}
