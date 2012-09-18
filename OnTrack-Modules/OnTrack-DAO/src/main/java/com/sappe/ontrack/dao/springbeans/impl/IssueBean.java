package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.model.issues.Issue;

public class IssueBean implements IssueManager{
	
	public Issue create(Issue entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Issue read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public Issue update(Issue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteO(Issue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		// TODO Auto-generated method stub
		
	}

	public List<Issue> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
