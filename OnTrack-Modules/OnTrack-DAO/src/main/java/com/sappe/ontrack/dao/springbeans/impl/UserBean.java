package com.sappe.ontrack.dao.springbeans.impl;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
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

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ServiceException;
import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.Member;
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

	public List<Member> contactsByUserName(String userName,String password) throws ServiceException, IOException {
		List<Member> members = buildMembers(userName,password);
		return members;
	}

	private List<Member> buildMembers(String userName,String password)throws ServiceException, IOException {
		ContactsService service = new ContactsService("OScar");
		if (userName == null || password == null) {
			return new ArrayList<Member>();
		}
		service.setUserCredentials(userName, password);

		List<Member> members = new ArrayList<Member>();

		// Request the feed
		URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		ContactFeed resultFeed = service.getFeed(feedUrl, ContactFeed.class);
		// Print the results
		System.out.println(resultFeed.getTitle().getPlainText());
		for (ContactEntry entry : resultFeed.getEntries()) {
			Member.Builder memberBuild = new Member.Builder();
			if (entry.hasName()) {
				Name name = entry.getName();
				if (name.hasFullName()) {
					String fullNameToDisplay = name.getFullName().getValue();
					memberBuild.name(fullNameToDisplay);
				}else{
					System.out.println("FullName not found");
				}
				System.out.println("Email addresses:");
				for (Email email : entry.getEmailAddresses()) {
					System.out.print(" " + email.getAddress());
					memberBuild.email(email.getAddress());
				}

				Link photoLink = entry.getContactPhotoLink();
				String photoLinkHref = photoLink.getHref();
				memberBuild.photoLink(photoLinkHref);
				System.out.println("Photo Link: " + photoLinkHref);

				Member member = memberBuild.build();
				members.add(member);
			}
		}

		return members;
	}





}
