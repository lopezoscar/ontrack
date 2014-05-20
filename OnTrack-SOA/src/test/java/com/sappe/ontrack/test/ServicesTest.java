package com.sappe.ontrack.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import junit.framework.Assert;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sappe.ontrack.services.Service;
import com.sappe.ontrack.services.ServiceParameter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class ServicesTest extends BaseTest{
	
	@Autowired 
	private ListableBeanFactory beanFactory;

	@Autowired
	private BeanFactory factory;
	
//	@PersistenceContext
//	private EntityManager em;
	
//	public final static String baseURL = "http://localhost:8080/OnTrack-SOA/";
	
	@Test
//	@Transactional
	public void listServices(){
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
		for (String service : methodsByClass.keySet()) {
			List<Service> services = methodsByClass.get(service);
			for (Service srv : services) {
				em.getTransaction().begin();
				em.persist(srv);
				em.getTransaction().commit();
			}
		}
		response = toJson(methodsByClass);
		
//		return Response.ok().entity(response).header("Access-Control-Allow-Origin", "*").build();
		
//		return response;
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
				
				method.setPath(buildPath(null, servicePath, methodName, httpGET, method.getParameters()));
				methods.add(method);
			}
		}
		
		return methods;
	}
	
	private String buildPath(String baseURL,String servicePath,String methodName,boolean httpGET,List<ServiceParameter> parameters){
		StringBuffer sb = new StringBuffer();
//		sb.append(baseURL);
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
	
	private List<ServiceParameter> extractParametersFromMethod(Method m){
		List<ServiceParameter> result = new ArrayList<ServiceParameter>();
		Class[] parameters = m.getParameterTypes();
		for (Class param : parameters) {
			ServiceParameter srvParam = new ServiceParameter();
			srvParam.setName(param.getCanonicalName());
			result.add(srvParam);
//			System.out.println(param);
		}
		return result;
	}
	
	private List<ServiceParameter> extractParametersFromMethodPOST(Method m) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		List<ServiceParameter> result = new ArrayList<ServiceParameter>();
		Class[] parameters = m.getParameterTypes();
		for (Class param : parameters) {
			ServiceParameter srvParam = new ServiceParameter();
			Object o = param.newInstance();
			srvParam.setBody(toJson(o));
			srvParam.setName(param.getCanonicalName());
			result.add(srvParam);
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
