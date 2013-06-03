package com.sappe.ontrack.dao.springbeans.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.dao.springbeans.interfaces.MailManager;
import com.sappe.ontrack.dao.springbeans.interfaces.NotificationManager;
import com.sappe.ontrack.model.notifications.NotificationDTO;

public class NotificationBean implements NotificationManager{
	
	@Autowired
	private MailManager mailManager;

	public void sendEmails(NotificationDTO dto) throws NotificatorException {
		mailManager.sendEmail(dto.getFrom(), dto.getTo(), dto.getCc(), dto.getSubject(), dto.getBody(), dto.getPriority());
	}

}
