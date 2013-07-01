package com.sappe.ontrack.sdk.interfaces;

import com.sappe.ontrack.model.notifications.NotificationDTO;

public interface NotificationService {
	
	void sendMail(NotificationDTO notifactionDTO);

}
