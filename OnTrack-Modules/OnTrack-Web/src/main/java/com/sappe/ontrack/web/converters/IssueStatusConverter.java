package com.sappe.ontrack.web.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sappe.ontrack.web.util.ReflectionUtil;

@FacesConverter("issueStatusConverter")
public class IssueStatusConverter implements Converter{

	private List<?> values;
	
	private String key;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		for (Object value : values) {
			if (ReflectionUtil.getStringProperty(value, key).equals(str)) {
				return value;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj != null) {
			if (obj instanceof String) {
				return obj.toString();
			}
			return ReflectionUtil.getStringProperty(obj, key);
		}
		return "?";
	}


}
