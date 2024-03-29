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

import com.sappe.ontrack.dao.springbeans.interfaces.IssueStatusManager;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;

@Component
public class IssueStatusBean implements IssueStatusManager {

	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;
	@Transactional
	public IssueStatus create(IssueStatus entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		
		em.persist(entity);
		// TODO Auto-generated method stub
		return entity;
	}

	public IssueStatus read(Serializable primaryKey)
			throws IllegalStateException, IllegalArgumentException {
				
		return em.find(IssueStatus.class, primaryKey);		
	}
	
	@Transactional
	public IssueStatus update(IssueStatus entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	
	
	
	public void delete(IssueStatus entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}

	@SuppressWarnings("unchecked")
	public List<IssueStatus> getAllIssueStatus() {
		Query q = em.createNamedQuery("selectAllIssueStatus");
		List<IssueStatus> issuesStatus = q.getResultList();
		return issuesStatus;
	}

	public List<IssueStatusByWorkflow> getIssueStatusByIssueType(IssueType issueType,Project project) {
		Query q = em.createQuery("select isw from IssueStatusByWorkflow isw where isw.wf.issueType = :it and isw.wf.project = :project");
		q.setParameter("it", issueType);
		q.setParameter("project", project);
		List<IssueStatusByWorkflow> status = q.getResultList();
		return status;
	}

	public List<IssueStatus> getIssueStatusByDesc(String desc) {
		return em.createNamedQuery("getIssueStatusByDesc").setParameter("description", desc).getResultList();
	}
	
	
	
}

