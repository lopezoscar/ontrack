package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueTypeManager;
import com.sappe.ontrack.model.issues.IssueType;

public class IssueTypeBean implements IssueTypeManager{
	
	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;
	@Transactional
	public IssueType create(IssueType entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity;
	}
	public IssueType read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
		return em.find(IssueType.class, primaryKey);
	}
	public IssueType update(IssueType entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}
	public void delete(IssueType entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.detach(entity);
		
	}
	public List<IssueType> getAllIssueType() {
		Query q = em.createNamedQuery("selectAllIssueType");
		List<IssueType> issueTypes = q.getResultList();
		return issueTypes;
	}
}
