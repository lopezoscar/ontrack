package com.sappe.ontrack.dao.exceptions;


public class NotificatorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8438910402271265918L;
	
	private String msg;
	
	public NotificatorException(String msg){
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
