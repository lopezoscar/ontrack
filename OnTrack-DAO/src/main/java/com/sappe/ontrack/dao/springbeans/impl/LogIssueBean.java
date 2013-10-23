package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.LogIssueManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueAction;
import com.sappe.ontrack.model.issues.LogIssue;

//@Aspect
@Component
public class LogIssueBean implements LogIssueManager{
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public LogIssue create(LogIssue entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity;
	}

	public LogIssue read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(LogIssue.class, primaryKey);
	}

	@Transactional
	public LogIssue update(LogIssue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		return em.merge(entity);
	}

	@Transactional
	public void delete(LogIssue entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
	}
	
//	@AfterReturning("execution(* com.sappe.ontrack.IssueBean.create(..)) || execution(* com.sappe.ontrack.IssueBean.update(..))")
	@Transactional
	public void addLogToIssue(Issue issue,IssueAction action){
		LogIssue log = new LogIssue();
		log.setDate(new Date().toString());
		log.setAction(action);
		String description = String.format(action.getAction(), issue.getReporter(),issue.getTitle(),issue.getId());
		log.setDescription(description);
		log.setIssue(issue);
		create(log);
	}

}
