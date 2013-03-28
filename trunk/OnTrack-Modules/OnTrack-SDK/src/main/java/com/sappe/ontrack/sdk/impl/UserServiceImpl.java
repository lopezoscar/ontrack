package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;

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
	
	
	

}
