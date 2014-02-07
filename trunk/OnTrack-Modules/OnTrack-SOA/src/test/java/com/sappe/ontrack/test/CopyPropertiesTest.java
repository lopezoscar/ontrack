package com.sappe.ontrack.test;

import java.lang.reflect.Field;

import org.codehaus.jackson.map.util.BeanUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import copyclasses.Nombre;
import copyclasses.Person;
import copyclasses.PersonB;

public class CopyPropertiesTest {
	
	
	@Test
	public void test(){
		Person a = new Person();
		Nombre nombre = new Nombre();
		nombre.setApellido("Lopez");
		nombre.setNombre("Oscar");
		a.setNombre(nombre);
		
		PersonB b = new PersonB();
		
		reflectionTest(a, b);
		Assert.assertNotNull(b.getNombre());
		Assert.assertNotNull(b.getNombre().getApellido());
		Assert.assertNotNull(b.getNombre().getNombre());
		
//		try {
//			
//			BeanUtils.copyProperties(b, a);
//			
//			System.out.println(b.getNombre());
//			System.out.println(b.getNombre().getApellido());
//			
//			
//			
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public void reflectionTest(Object src,Object target){
		Class<?> clazz = src.getClass();
		Field[] fields = clazz.getDeclaredFields();
		int fieldsLength = fields.length;
		for (int i = 0; i <fieldsLength ; i++) {
			Field var = fields[i];
			//Access to private var
			var.setAccessible(true);
			if(var!= null){
				try {
					Object value = var.get(src);
					if(!isPrimitiveType(value.getClass())){
							try {
								Object newInstanceTarget = instance(value.getClass().getName());
								reflectionTest(value,newInstanceTarget);
							} catch (InstantiationException | ClassNotFoundException e) {
								e.printStackTrace();
							}
					}else{
//						Object newInstanceTarget;
						var.set(var.getName(), value);
//						try {
//							newInstanceTarget = instance(value.getClass().getName());
//							BeanUtils.copyProperties(value, newInstanceTarget);
//							
//						} catch (InstantiationException | ClassNotFoundException e) {
//							e.printStackTrace();
//						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Object instance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class<?> clazz = Class.forName(className);
		Object object = clazz.newInstance();
		return object;
	}
	
	private boolean isPrimitiveType(Class<?> clazz){
		if(String.class.equals(clazz))return true;
		if(Integer.class.equals(clazz))return true;
		if(Long.class.equals(clazz))return true;
		if(Float.class.equals(clazz))return true;
		if(Double.class.equals(clazz))return true;
		if(Boolean.class.equals(clazz))return true;
		
		System.out.println("Clase:"+clazz.getName());
		String className = clazz.getName();
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
