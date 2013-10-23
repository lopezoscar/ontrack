package com.sappe.ontrack.model.notifications;

public enum PriorityEnum {
	
	LOW("5"),
	MEDIUM("3"),
	HIGH("1");
	
	private String number;
	
	private PriorityEnum(String number){
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
