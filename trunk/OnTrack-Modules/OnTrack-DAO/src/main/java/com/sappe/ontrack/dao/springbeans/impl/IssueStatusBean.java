package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueStatusManager;
import com.sappe.ontrack.model.issues.IssueStatus;

@Component
public class IssueStatusBean implements IssueStatusManager {

	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;
	
	public IssueStatus create(IssueStatus entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public IssueStatus read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
				
		return em.find(IssueStatus.class, primaryKey);		
	}

	public IssueStatus update(IssueStatus entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteO(IssueStatus entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		// TODO Auto-generated method stub
		
	}

	public void delete(IssueStatus entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		// TODO Auto-generated method stub
		
	}

}
