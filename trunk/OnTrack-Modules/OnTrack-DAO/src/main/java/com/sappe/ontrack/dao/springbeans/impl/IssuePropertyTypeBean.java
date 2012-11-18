package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyTypeManager;
import com.sappe.ontrack.model.issues.IssuePropertyType;

public class IssuePropertyTypeBean implements IssuePropertyTypeManager, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em; 
	
	@Transactional
	public IssuePropertyType create(IssuePropertyType entity)
			throws EntityExistsException, IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		em.persist(entity);
		return null;
	}

	public IssuePropertyType read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
		
		return em.find(IssuePropertyType.class, primaryKey);
	}

	@Transactional
	public IssuePropertyType update(IssuePropertyType entity)
			throws IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
	
		return em.merge(entity);
	}

	@Transactional
	public void delete(IssuePropertyType entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {

		em.remove(entity);
	}

	public List<IssuePropertyType> getAllIssuePropertyTypes() {

		return em.createNamedQuery("allIssuePropertyTypes").getResultList();
	}

	
}
