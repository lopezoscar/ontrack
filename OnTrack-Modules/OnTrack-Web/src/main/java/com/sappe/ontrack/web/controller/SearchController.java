package com.sappe.ontrack.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="searchctrl")
@ViewScoped
public class SearchController implements Serializable{

	private String keyword;
	
	public void search(){
		
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	
	
}
