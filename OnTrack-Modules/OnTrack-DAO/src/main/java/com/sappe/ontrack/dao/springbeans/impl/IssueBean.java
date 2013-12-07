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
import com.sappe.ontrack.model.issues.IssueComment;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.users.User;

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

	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByProjectId(Long id) {
//		List<Issue> issues = em.createQuery("select i from Issue i where i.project.id = :projectid").setParameter("projectid", id).getResultList();
		List<Issue> issues = em.createNamedQuery("getIssuesByProjectId").setParameter("projectid", id).getResultList();
		return issues;
		
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByOwnerId(Long id) {
		List<Issue> issues = em.createNamedQuery("getIssuesByOwnerId").setParameter("ownerid", id).getResultList();
		return issues;
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByReporter(String reporter) {
		List<Issue> issues = em.createNamedQuery("getIssuesByReporter").setParameter("reporter", reporter).getResultList();
		return issues;
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByStatus(IssueStatus status) {
		List<Issue> issues = em.createNamedQuery("getIssuesByStatus").setParameter("status", status).getResultList();
		return issues;
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByType(IssueType type) {
		List<Issue> issues = em.createNamedQuery("getIssuesByIssueType").setParameter("type", type).getResultList();
		return issues;
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByCode(String code) {
		List<Issue> issues = em.createNamedQuery("getIssueByCode").setParameter("code", code).getResultList();
		return issues;
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuesByUserFromProjects(User user){
		List<Issue> issues = em.createNamedQuery("getIssuesByUserFromProject").setParameter("user", user).getResultList();
		return issues;
	}

	@Transactional
	public List<IssueComment> addCommentToIssue(IssueComment comment) {
		Issue issue = read(comment.getIssueID());
		List<IssueComment> comments = issue.getComments();
		em.persist(comment);
		comments.add(comment);
		update(issue);
		return comments;
	}

	public void reassignOwner(User currentOwner, User newOwner) {
		List<Issue> issues = getIssuesByOwnerId(currentOwner.getId());
		for (Issue issue : issues) {
			issue.setOwner(newOwner);
			em.merge(issue);
		}
	}
	
	
}
