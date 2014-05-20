package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueActionManager;
import com.sappe.ontrack.model.issues.IssueAction;

public class IssueActionBean implements IssueActionManager{
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public IssueAction create(IssueAction entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity;
	}

	public IssueAction read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return em.find(IssueAction.class, primaryKey);
	}

	@Transactional
	public IssueAction update(IssueAction entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(IssueAction entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
	}

}
