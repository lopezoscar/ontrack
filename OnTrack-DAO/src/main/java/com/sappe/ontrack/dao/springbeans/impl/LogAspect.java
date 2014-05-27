package com.sappe.ontrack.dao.springbeans.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {
	
	@After("execution(* com.sappe.ontrack.CRUD.create(..))")
	public void create(JoinPoint joinPoint){
		//TODO Should create a log object and persist them
	};
	@After("execution(* com.sappe.ontrack.CRUD.read(..))")
	public void read(JoinPoint joinPoint){
		//TODO Should create a log object and persist them		
	};
	@After("execution(* com.sappe.ontrack.CRUD.update(..))")
	public void update(JoinPoint joinPoint){
		//TODO Should create a log object and persist them
	};
	@After("execution(* com.sappe.ontrack.CRUD.delete(..))")
	public void delete(JoinPoint joinPoint){
		//TODO Should create a log object and persist them
	};
	
}
