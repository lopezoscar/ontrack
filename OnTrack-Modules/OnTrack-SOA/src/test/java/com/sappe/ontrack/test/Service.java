package com.sappe.ontrack.test;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1395802768438378868L;

	private String name;
	
	private String path;
	
	private String method;
	
	private List<String> parameters;
	
	private String clazz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	
	
	
}
