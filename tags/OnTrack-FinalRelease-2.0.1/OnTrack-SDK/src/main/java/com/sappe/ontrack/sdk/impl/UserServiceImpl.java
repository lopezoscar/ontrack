package com.sappe.ontrack.sdk.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.model.users.Member;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;

@Component
public class UserServiceImpl implements UserService,Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1712777648566900615L;

	public void login() {
		
	}
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		String response = HTTPManager.get("/usersrv/getallusers");
		users.addAll(fromJSON(new TypeReference<List<User>>(){},response));
		return users;
	}
	
	private static <T> T fromJSON(final TypeReference<T> type,final String jsonPacket) {
		   T data = null;
	
		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return data;
	}

	public User userByUserName(String userName) {
		StringBuilder url = new StringBuilder();
		url.append("/usersrv/userbyusername/");
		String response = HTTPManager.post(url.toString(),userName,false);
		User user = fromJSON(new TypeReference<User>(){}, response);
		return user;
	}

	public List<Member> contactsByUserName(User user) {
		List<Member> members = new ArrayList<Member>();
		StringBuilder url = new StringBuilder();
		url.append("/usersrv/contacts/");
		ObjectMapper mapper = new ObjectMapper();
		String content = null;
		try {
			content = mapper.writeValueAsString(user);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = HTTPManager.post(url.toString(),content,false);
		
		
		members.addAll(fromJSON(new TypeReference<List<Member>>(){},response));
		return members;
	}

	public User userByEmail(String email) {
		StringBuilder url = new StringBuilder();
		url.append("/usersrv/userbyemail/");
		String response = HTTPManager.post(url.toString(),email,false);
		User user = fromJSON(new TypeReference<User>(){}, response);
		return user;
	}

	public User createUser(User user) {
		ObjectMapper mapper = new ObjectMapper();
		String content = null;
		try {
			content = mapper.writeValueAsString(user);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = HTTPManager.post("/usersrv/createuser", content, false);
		User createdUser = Mapper.fromJSON(new TypeReference<User>() {}, response);
		return createdUser;
	}
	
	
	

}
