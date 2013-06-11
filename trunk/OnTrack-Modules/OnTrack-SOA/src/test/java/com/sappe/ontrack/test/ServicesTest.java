package com.sappe.ontrack.test;

import java.util.Map;

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
	@Test
	public void test() throws ClassNotFoundException{
//		String[] beans = beanFactory.getBeanDefinitionNames();
		Map<String,Object> beans = beanFactory.getBeansWithAnnotation(Path.class);
		for (String bean : beans.keySet()) {
			System.out.println(bean);
			System.out.println(beans.get(bean));
		}
		
//		int methods = 0;
//		for (String clazz : beans) {
//			factory.getBean(arg0)
//			Class testClass = Class.forName(clazz);
//			
//			for (Method m : testClass.getDeclaredMethods()) {
//				if (m.isAnnotationPresent(Path.class)) {
//					methods++;
//					try {
//						m.invoke(null);
////						passed++;
//					} catch (InvocationTargetException wrappedExc) {
//						Throwable exc = wrappedExc.getCause();
//						System.out.println(m + " failed: " + exc);
//					} catch (Exception exc) {
//						System.out.println("INVALID @Test: " + m);
//					}
//				}
//			}
//			
//			System.out.println(methods);
//		}
		
		
	}
}
