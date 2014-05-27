package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyManager;
import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;

@Component
public class IssuePropertyBean implements IssuePropertyManager,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6247927147212553822L;
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public IssueProperty create(IssueProperty entity)
			throws EntityExistsException, IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		em.persist(entity);
		return entity;
	}

	public IssueProperty read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
		return em.find(IssueProperty.class, primaryKey);
	}

	@Transactional
	public IssueProperty update(IssueProperty entity)
			throws IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(IssueProperty entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}

	@SuppressWarnings("unchecked")
	public List<IssuePropertyType> getAllIssuePropertyTypes() {
		return em.createNamedQuery("allIssuePropertyTypes").getResultList();
	}
	
	public List<IssueProperty> getIssuePropertyByDesc(String desc){
		return em.createQuery("select ip from IssueProperty ip where ip.description = :description").setParameter("description", desc).getResultList();
	}

}
