package com.sappe.ontrack.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class ServicesTest {
	
	@Autowired 
	private ListableBeanFactory beanFactory;

	@Autowired
	private BeanFactory factory;
	
	public final static String baseURL = "http://localhost:8080/OnTrack-SOA/";
	
	
	@Test
	public void test() throws ClassNotFoundException{
//		String[] beans = beanFactory.getBeanDefinitionNames();
		Map<String,Object> beans = beanFactory.getBeansWithAnnotation(Path.class);
		List<MethodModelTest> methods = new ArrayList<MethodModelTest>();
		Map<String,List<MethodModelTest>> methodsByClass = new HashMap<String,List<MethodModelTest>>();
		for (String bean : beans.keySet()) {
			methods = searchServicesIntoBean(beans.get(bean));
			methodsByClass.put(bean, methods);
		}
		int total = 0;
		for (String key : methodsByClass.keySet()) {
			List<MethodModelTest> list =  methodsByClass.get(key);
			int size = list.size();
			total+=size;
			System.out.println("Class: "+key+" - Cant Methods: "+size+"\n");
			for (MethodModelTest m : list) {
				System.out.println(m.getPath());
				System.out.println("Method:"+m.getMethod());
			}
			System.out.println("\n");
		}
		System.out.println(total);
//		for (MethodModelTest m : methods) {
////			System.out.println("Method: "+m.getName()+" - ");
//			System.out.println(" - HTTP METHOD: "+m.getMethod());
//			System.out.println(m.getPath()+"\n");
////			System.out.println(m.getParameters().size());
//		}
	}
	
	private List<MethodModelTest> searchServicesIntoBean(Object bean) throws ClassNotFoundException{
		List<MethodModelTest> methods = new ArrayList<MethodModelTest>();
		Class clazz = Class.forName(bean.getClass().getCanonicalName());
		
		for (Method m : clazz.getDeclaredMethods()) {
			int cantMethods = 0;
			Path pathSrv = (Path)clazz.getAnnotation(Path.class);
			String servicePath = pathSrv.value();
			if (m.isAnnotationPresent(Path.class)) {
				cantMethods++;
				MethodModelTest method = new MethodModelTest();
				boolean httpGET = false;
				
				Path methodPath = m.getAnnotation(Path.class);
				String methodName = methodPath.value();
				
				if(m.isAnnotationPresent(POST.class)){
					method.setMethod("POST");
				}
				if(m.isAnnotationPresent(GET.class)){
					httpGET = true;
					method.setMethod("GET");
				}
				
				
				method.setName(m.getName());
				method.setParameters(extractParametersFromMethod(m));
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
		if(!httpGET){
			for (String param : parameters) {
				sb.append("{");
				sb.append(param);
				sb.append("}");
			}
		}
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
}
