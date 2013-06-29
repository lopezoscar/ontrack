package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;
import java.util.Map;

import com.sappe.ontrack.services.Service;

public interface ServiceManager extends CRUD<Service>{
	
	Map<String,List<Service>> getAllServices();
	
}
