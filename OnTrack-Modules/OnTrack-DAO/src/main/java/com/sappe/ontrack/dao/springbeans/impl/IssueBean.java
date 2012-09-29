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

import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.model.issues.Issue;

@Component
public class IssueBean implements IssueManager{
	
	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;
	
	@Transactional
	public Issue create(Issue entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity;
	}
	
	@Transactional
	public Issue read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(Issue.class, (Long)primaryKey);
	}

	@Transactional
	public Issue update(Issue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(Issue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}

	public List<Issue> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
