package com.sappe.ontrack.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sappe.ontrack.model.notifications.NotificationDTO;
import com.sappe.ontrack.model.users.Member;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.NotificationService;
import com.sappe.ontrack.sdk.interfaces.UserService;
import com.sappe.ontrack.security.UserDetailsViewModel;

@ManagedBean(name="membersctrl")
public class MembersController {
	
	@ManagedProperty(value="#{usersrv}")
	private UserService userService;
	
	@ManagedProperty(value="#{notificationsrv}")
	private NotificationService notificationService;
	
	private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	private List<Member> members = new ArrayList<Member>();
	
	private List<Member> selectedMembers = new ArrayList<Member>();
	
	public List<Member> contactsFromUser(){
		if(!members.isEmpty()){
			return members;
		}
//		String name = auth.getName(); //get logged in username
		UserDetailsViewModel userDetails = (UserDetailsViewModel)auth.getPrincipal();
		User user = userDetails.getUser();
//		User user = userService.userByUserName(user.get);
		members = userService.contactsByUserName(user);
		return members;
	}
	
	public void notifySelectedMembers(){
		NotificationDTO dto = new NotificationDTO();
		dto.setFrom("noreply@ontrack.com.ar");
		List<String> to = new ArrayList<String>();
		to.add("lopezoscar.job@gmail.com");
		for (Member member : selectedMembers) {
//			to.add(member.getEmail());
		}
		String name = auth.getName();
		User user = userService.userByUserName(name);
		StringBuilder sb = new StringBuilder();
		sb.append(user.completeName());
		
		sb.append(" te invita a ingresar a OnTrack");
		sb.append("\nURL: http://www.ontrack.com.ar");
		dto.setSubject(sb.toString());
		dto.setTo(to);
		dto.setBody(sb.toString());
		
		notificationService.sendMail(dto);
		
	}
	
	public void selectAllMembers(){
		for (Member member : selectedMembers) {
			member.setSelected(Boolean.TRUE);
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
//	public List<SelectItem> itemsMembers(){
//		
//	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Member> getSelectedMembers() {
		return selectedMembers;
	}

	public void setSelectedMembers(List<Member> selectedMembers) {
		this.selectedMembers = selectedMembers;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	
	
	

}
