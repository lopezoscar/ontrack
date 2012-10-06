package com.sappe.ontrack.web.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.sappe.ontrack.web.util.ReflectionUtil;



public class GenericListConverter implements Converter{

		
		private List<?> values;
		
		private String key;
		
		public GenericListConverter(List<?> values, String key) {
			this.values = values;
			this.key = key;
		}
		
		public Object getAsObject(FacesContext context, UIComponent component, String str) {
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

		public String getAsString(FacesContext context, UIComponent component, Object obj) {		
			if (obj != null) {
				if (obj instanceof String) {
					return obj.toString();
				}
				return ReflectionUtil.getStringProperty(obj, key);
			}
			return "?";
		}

}
