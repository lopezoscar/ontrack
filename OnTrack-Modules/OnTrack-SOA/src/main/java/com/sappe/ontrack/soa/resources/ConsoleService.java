package com.sappe.ontrack.soa.resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.ServiceManager;
import com.sappe.ontrack.services.Service;


@Component
@Path("consolesrv")
public class ConsoleService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4425419998945928926L;

	public final static String baseURL = "http://localhost:8080/OnTrack-SOA/";
	
	@Autowired
	private ServiceManager serviceManager;
	
	@GET
	@Path("listservices")
	public Response listServices(){
		Map<String,List<Service>> services = serviceManager.getAllServices();
		String response = toJson(services);
		return Response.ok().entity(response).build();
	}
	
	private String toJson(Object o){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
			System.out.println(json);
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
		return json;
	}
}
