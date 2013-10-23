package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.ProcessHistoryManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.ProcessHistory;

public class ProcessHistoryBean implements ProcessHistoryManager {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public ProcessHistory create(ProcessHistory entity)
			throws EntityExistsException, IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		em.persist(entity);
		return entity;
	}

	public ProcessHistory read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return em.find(ProcessHistory.class, primaryKey);
	}

	@Transactional
	public ProcessHistory update(ProcessHistory entity)
			throws IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		// TODO Auto-generated method stub
		return em.merge(entity);
	}

	@Transactional
	public void delete(ProcessHistory entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
	}

	public void addEntryToHistory(Issue issue) {
		ProcessHistory history = new ProcessHistory();
		history.setDate(new Date().toString());
		history.setStatus(issue.getCurrentStatus().getDescription());
		history.setIssue(issue);
		create(history);
	}

}
