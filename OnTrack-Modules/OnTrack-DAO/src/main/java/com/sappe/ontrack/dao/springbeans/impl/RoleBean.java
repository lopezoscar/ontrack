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

import com.sappe.ontrack.dao.springbeans.interfaces.RoleManager;
import com.sappe.ontrack.model.users.Role;

@Component
public class RoleBean implements RoleManager {
	
	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;

	@Transactional
	public Role create(Role entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity; 
	}

	public Role read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(Role.class, (Integer)primaryKey);
	}

	@Transactional
	public Role update(Role entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(Role entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles(){
		return em.createNamedQuery("getAllRoles").getResultList();
	}

}
