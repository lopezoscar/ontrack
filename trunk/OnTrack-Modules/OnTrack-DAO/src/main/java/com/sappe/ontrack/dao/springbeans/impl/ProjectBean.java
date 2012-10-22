package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.ProjectManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;

@Component
public class ProjectBean implements ProjectManager{

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Project create(Project entity) throws EntityExistsException,
	IllegalStateException, IllegalArgumentException,
	TransactionRequiredException {
		// TODO Auto-generated method stub
		 em.persist(entity);
		return entity; 
	}

	@Transactional
	public Project read(Serializable primaryKey) throws IllegalStateException,
	IllegalArgumentException {
		// TODO Auto-generated method stub
		return em.find(Project.class, primaryKey);
	}

	@Transactional
	public Project update(Project entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		em.merge(entity);
		return entity;
	}

	@Transactional
	public void delete(Project entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException,
	PersistenceException {
		// TODO Auto-generated method stub
		em.remove(entity);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Project> getAllProjects() {
		List<Project> projects = em.createNamedQuery("getAllProjects").getResultList();
//		for (Project project : projects) {
//			List<Issue> issues = project.getIssues();
//			for (Issue issue : issues) {
//				issue = initializeAndUnproxy(issue);
//			}
//			issues = initializeAndUnproxy(issues);
//			project.setIssues(issues);
//			List<IssueType> types = project.getIssueTypes();
//			types = initializeAndUnproxy(types);
//			project.setIssueTypes(types);
//		}
//		List<Project> projects = new ArrayList<Project>();
		return projects;
	}

	public static boolean isUninitialized(Object proxy) {
		if ( proxy instanceof HibernateProxy ) {
			return ( ( HibernateProxy ) proxy ).getHibernateLazyInitializer().isUninitialized();
		}
		else if ( proxy instanceof PersistentCollection ) {
			return ( ( PersistentCollection ) proxy ).wasInitialized();
		}
		else {
			return true;
		}
	}
	
	public static <T> T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}



}
