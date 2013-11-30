package com.sappe.ontrack.model.notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationDTO {
	
	private String from;
	
	private List<String> to = new ArrayList<String>();
	
	private List<String> cc = new ArrayList<String>();
	
	private List<String> cco = new ArrayList<String>();
	
	private String subject;
	
	private String body;
	
	private PriorityEnum priority;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getCco() {
		return cco;
	}

	public void setCco(List<String> cco) {
		this.cco = cco;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	
	

}
