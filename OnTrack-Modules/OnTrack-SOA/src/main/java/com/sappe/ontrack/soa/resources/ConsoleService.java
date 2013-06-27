package com.sappe.ontrack.soa.resources;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	private ListableBeanFactory beanFactory;

	@Autowired
	private BeanFactory factory;
	
	@GET
	@Path("listservices")
	public String listServices(){
		String response = "";
		Map<String,Object> beans = beanFactory.getBeansWithAnnotation(Path.class);
		List<Service> methods = new ArrayList<Service>();
		Map<String,List<Service>> methodsByClass = new LinkedHashMap<String,List<Service>>();
		try {
			for (String bean : beans.keySet()) {
				try {
					methods = searchServicesIntoBean(beans.get(bean));
					methodsByClass.put(bean, methods);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		response = toJson(methodsByClass);
		return response;
	}

	private List<Service> searchServicesIntoBean(Object bean) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<Service> methods = new ArrayList<Service>();
		Class clazz = Class.forName(bean.getClass().getCanonicalName());
		
		for (Method m : clazz.getDeclaredMethods()) {
			int cantMethods = 0;
			Path pathSrv = (Path)clazz.getAnnotation(Path.class);
			String servicePath = pathSrv.value();
			if (m.isAnnotationPresent(Path.class)) {
				cantMethods++;
				Service method = new Service();
				boolean httpGET = false;
				
				Path methodPath = m.getAnnotation(Path.class);
				String methodName = methodPath.value();
				
				if(m.isAnnotationPresent(POST.class)){
					method.setMethod("POST");
					method.setParameters(extractParametersFromMethodPOST(m));
				}
				if(m.isAnnotationPresent(GET.class)){
					httpGET = true;
					method.setMethod("GET");
					method.setParameters(extractParametersFromMethod(m));
				}
				
				method.setClazz(clazz.getCanonicalName());
				method.setName(m.getName());
				
				method.setPath(buildPath(baseURL, servicePath, methodName, httpGET, method.getParameters()));
				methods.add(method);
			}
		}
		
		return methods;
	}
	
	private String buildPath(String baseURL,String servicePath,String methodName,boolean httpGET,List<String> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append(baseURL);
		sb.append(servicePath);
		sb.append("/");
		sb.append(methodName);
		sb.append("/");
//		if(!httpGET){
//			for (String param : parameters) {
//				sb.append("{");
//				sb.append(param);
//				sb.append("}");
//			}
//		}
		String url = sb.toString();
		url = clean(url);
		return url;
	}
	
	private String clean(String url){
		url = url.replaceAll("//", "/");
		url = url.replaceAll("http:/", "http://");
		return url;
	}
	
	private List<String> extractParametersFromMethod(Method m){
		List<String> result = new ArrayList<String>();
		Class[] parameters = m.getParameterTypes();
		for (Class param : parameters) {
			result.add(param.getCanonicalName());
//			System.out.println(param);
		}
		return result;
	}
	
	private List<String> extractParametersFromMethodPOST(Method m) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<String> result = new ArrayList<String>();
		Class[] parameters = m.getParameterTypes();
		for (Class param : parameters) {
			Object o = param.newInstance();
			result.add(toJson(o));
		}
		return result;
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
