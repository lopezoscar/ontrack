package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;

public interface IssuePropertyManager extends CRUD<IssueProperty>{
	
	List<IssuePropertyType> getAllIssuePropertyTypes();
	
	List<IssueProperty> getIssuePropertyByDesc(String desc);

}
