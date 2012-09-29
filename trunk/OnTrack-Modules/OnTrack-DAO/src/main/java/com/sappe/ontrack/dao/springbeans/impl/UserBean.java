package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;

public class UserBean implements UserManager{
	
	@PersistenceContext
	private EntityManager em;
	
	public User create(User entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public User read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		User user = (User)em.find(User.class, primaryKey);
		return user;
	}

	public User update(User entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteO(User entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		// TODO Auto-generated method stub
		
	}

	public List<User> getAllUser() {
		Query q = em.createNamedQuery("selectAllUser");
		List<User> users = q.getResultList();
		List<Role> roles = new ArrayList<Role>();
		for (User user : users) {
			for(Role role: user.getRoles()){
				Role r =  initializeAndUnproxy(role);
				roles.add(r);
				
			}
			user.setRoles(roles);
			roles = new ArrayList<Role>();
		}
		
		return users;
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
