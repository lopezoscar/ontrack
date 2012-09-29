package com.sappe.ontrack.dao.springbeans.interfaces;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

public interface CRUD<T> {

	T create(T entity) throws EntityExistsException, IllegalStateException,
	IllegalArgumentException, TransactionRequiredException;

	T read(Serializable primaryKey) throws IllegalStateException,
	IllegalArgumentException;

	T update(T entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException;

	void delete(T entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException,
	PersistenceException;

}
