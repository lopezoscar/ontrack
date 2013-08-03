package com.ontrack.test;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.sappe.ontrack.model.issues.Issue;

public class IssueEntryTest extends BaseTest{
	
	
	@Test
	public void test() throws Exception, JsonMappingException, IOException{
		File file = new File("C:\\issue.json");
		ObjectMapper mapper = new ObjectMapper();
		Issue issue = mapper.readValue(file, new TypeReference<Issue>() {});
		System.out.println(issue.getEntries().size());
//		fromJSON(new TypeReference<List<IssueEntry>>() {}, json) ;
		
		
//		IssueEntry entry = new IssueEntry();
//		entry.setValue("Un comentario");
//		User user = em.find(User.class,1l);
//		entry.setOwner(user);
//		entry.setProperty(em.find(IssueProperty.class, 1l));
		
//		List<EntryDocumentFile> files = new ArrayList<EntryDocumentFile>();
//		EntryDocumentFile file = new EntryDocumentFile();
//		file.setIssueEntry(entry);
//		file.setFileType("application/pdf");
//		file.setName("Un archivo pdf");
//		byte[] content = new byte[10];
//		content[0]=1;
//		file.setContent(content);
//		file.setLength-(content.length);
//		em.getTransaction().begin();
//		em.merge(file);
//		em.getTransaction().commit();
//		entry.setFiles(files);
		
//		toJson(entry);
		
		
//		persiste(entry);
		
	}

}
