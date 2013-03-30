package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;

@Component
public class UserBean implements UserManager{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public User create(User entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity; 
	}

	public User read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		User user = (User)em.find(User.class, primaryKey);
		return user;
	}

	@Transactional
	public User update(User entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	public void delete(User entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
		
	}

	public List<User> getAllUser() {
		Query q = em.createNamedQuery("selectAllUser");
		@SuppressWarnings("unchecked")
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
	
	
	@SuppressWarnings("unchecked")
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

	public User userByUserName(String userName) {
		User user = new User();
		try{
			user = (User)em.createNamedQuery("userByUserName").setParameter("userName", userName).getSingleResult();
		}catch(NoResultException nre){
			nre.printStackTrace();
		}catch(NonUniqueResultException nure){
			nure.printStackTrace();
		}
		
		return user;
	}
	
	
	

}
