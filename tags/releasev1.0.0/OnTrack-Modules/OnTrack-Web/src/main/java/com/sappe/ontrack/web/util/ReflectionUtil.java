package com.sappe.ontrack.web.util;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtils;

public class ReflectionUtil {


	/**
	 * Devuelve la propiedad de un objeto String
	 * @param object
	 * @param property
	 * @return
	 */
	public static String getStringProperty(Object object, String property) {
		String result = null;
		try {
			result = BeanUtils.getProperty(object, property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//TODO Mejorar este metodo ya que no valida tipo y usa rawtypes, debería usar Type Reference(T)
	public static String getId(Class entityClass){
		Field id = null;
		Class currentClass = entityClass;
		while (id == null && currentClass.getAnnotation(Entity.class) != null){
			Field[] fields = currentClass.getDeclaredFields();
			for(Field field : fields){
				if(field.getAnnotation(Id.class) != null){
					id = field;
					break;
				}
			}
			if(id != null){
				break;
			}
			currentClass = currentClass.getSuperclass();
		}
		if(id != null){
			return id.getName();
		}
		return null;
	}

}
