package com.sappe.ontrack.dao.springbeans.interfaces;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.model.notifications.NotificationDTO;

public interface NotificationManager{
	
	void sendEmails(NotificationDTO dto)throws NotificatorException;

}
