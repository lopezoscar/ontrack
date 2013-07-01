package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.WorkflowManager;
import com.sappe.ontrack.model.issues.Workflow;

public class WorkflowBean implements WorkflowManager{
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Workflow create(Workflow entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		 em.persist(entity);
		 return entity;
	}

	public Workflow read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(Workflow.class, primaryKey);
	}

	@Transactional
	public Workflow update(Workflow entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(Workflow entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}

}
