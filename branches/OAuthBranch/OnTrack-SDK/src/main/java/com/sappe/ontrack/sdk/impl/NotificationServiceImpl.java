package com.sappe.ontrack.sdk.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sappe.ontrack.model.notifications.NotificationDTO;
import com.sappe.ontrack.sdk.interfaces.NotificationService;

public class NotificationServiceImpl implements NotificationService{

	public void sendMail(NotificationDTO notifactionDTO) {
		StringBuilder url = new StringBuilder();
		url.append("/notificationsrv/sendEmail/");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(notifactionDTO);
			HTTPManager.post(url.toString(), content, false);
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


	}
	

}
