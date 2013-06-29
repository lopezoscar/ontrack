package com.sappe.ontrack.dao.springbeans.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.dao.springbeans.interfaces.ServiceManager;
import com.sappe.ontrack.services.Service;

public class ServiceBean implements ServiceManager{
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public Service create(Service entity) throws EntityExistsException,
			IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		em.persist(entity);
		return entity;
	}

	@Transactional
	public Service read(Serializable primaryKey) throws IllegalStateException,
			IllegalArgumentException {
		return em.find(Service.class, primaryKey);
	}

	@Transactional
	public Service update(Service entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException {
		return em.merge(entity);
	}

	@Transactional
	public void delete(Service entity) throws IllegalStateException,
			IllegalArgumentException, TransactionRequiredException,
			PersistenceException {
		em.remove(entity);
	}

	public Map<String,List<Service>> getAllServices() {
		List<String> classes = em.createQuery("SELECT s.clazz FROM Service s").getResultList();
		Map<String,List<Service>> services = new LinkedHashMap<String,List<Service>>();
		for (String clazz : classes) {
			List<Service> methods = (List<Service>)em.createQuery("FROM Service where clazz = :clazz").setParameter("clazz", clazz).getResultList();
			services.put(clazz, methods);
		}
		return services;
	}
	
	

}
