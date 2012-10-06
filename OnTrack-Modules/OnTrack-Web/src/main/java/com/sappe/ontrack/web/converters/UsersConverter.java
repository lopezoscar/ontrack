package com.sappe.ontrack.web.converters;

import java.util.List;

import javax.faces.convert.FacesConverter;


@FacesConverter("UsersConverter")
public class UsersConverter extends GenericListConverter{

	public UsersConverter(List<?> values, String key) {
		super(values, key);
		// TODO Auto-generated constructor stub
	}

}
