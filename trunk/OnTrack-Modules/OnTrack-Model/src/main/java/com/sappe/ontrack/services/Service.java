package com.sappe.ontrack.services;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6773355041405803214L;

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
