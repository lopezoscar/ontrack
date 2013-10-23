package com.sappe.ontrack.sdk.interfaces;

import java.util.List;

import com.sappe.ontrack.model.users.Member;
import com.sappe.ontrack.model.users.User;

public interface UserService {
	
	public void login();
	public List<User> getAllUsers();
	public User userByUserName(String userName);
	public List<Member> contactsByUserName(User user);

}
