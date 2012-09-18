package com.ontrack.test;

import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;

public class IssuePropertyTest extends BaseTest{
	
	@Test
	public void test(){
		IssuePropertyType type = new IssuePropertyType();
		type.setName("text");
		
		IssueProperty property = new IssueProperty();
		property.setJsType(type);
		property.setDescription("Texto");
		
//		persiste(type);
		persiste(property);
	}

}
