package com.sappe.ontrack.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;

public class MapperTest {
	
	@Test
	public void test(){
		Project project = new Project();
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		project.setName("SAPPE3");
		project.setIssueTypes(issueTypes);
		
		toJson(project);
		
		
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
	
	public static <T> T fromJSON(final TypeReference<T> type,final String jsonPacket) {
		   T data = null;
	
		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return data;
	}

}
