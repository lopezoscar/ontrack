package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.sdk.interfaces.RoleService;

public class RoleServiceImpl implements RoleService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3203538376654185962L;

	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<Role>();
		String url = "/rolesrv/getallroles";
		String response = HTTPManager.get(url);
		roles.addAll(fromJSON(new TypeReference<List<Role>>() {},response));
		return roles;
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

}
