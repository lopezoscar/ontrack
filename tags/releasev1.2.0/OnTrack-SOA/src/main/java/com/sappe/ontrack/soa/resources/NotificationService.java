package com.sappe.ontrack.soa.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.dao.springbeans.interfaces.NotificationManager;
import com.sappe.ontrack.model.notifications.NotificationDTO;

@Path("notificationsrv")
@Component
public class NotificationService {
	
	@Autowired
	@Qualifier("notificationbean")
	private NotificationManager notificationManager;
	
	@Path("/sendEmail/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendEmail(NotificationDTO dto){
		try {
			notificationManager.sendEmails(dto);
			return Response.ok().build();
		} catch (NotificatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}
