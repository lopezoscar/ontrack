package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.model.notifications.PriorityEnum;

public interface MailManager {

	boolean sendEmail(String from, String to, String cc, String subject, String body,PriorityEnum priority) throws NotificatorException;
	boolean sendEmail(String from, List<String> toAddresses, List<String> ccAddresses, String subject, String body,PriorityEnum priority) throws NotificatorException ;
	boolean sendEmail(String from, String to, String subject, String body,PriorityEnum priority) throws NotificatorException;
	boolean sendEmailWithAttachment(String from, String to, String cc, String subject, String body, String filename)throws NotificatorException;
}
