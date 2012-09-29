package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import com.sappe.ontrack.dao.springbeans.interfaces.RoleManager;
import com.sappe.ontrack.model.users.Role;

public class RoleBean implements RoleManager {
	
	@PersistenceContext(unitName = "ontrack")
	private EntityManager em;

	public Role create(Role entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public Role read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(Role.class, (Integer)primaryKey);
	}

	public Role update(Role entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	public void delete(Role entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
	em.remove(entity);
		
	}

}
