package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssuePropertyType;

public interface IssuePropertyTypeManager extends CRUD<IssuePropertyType>{
	
	List<IssuePropertyType> getAllIssuePropertyTypes();

}
