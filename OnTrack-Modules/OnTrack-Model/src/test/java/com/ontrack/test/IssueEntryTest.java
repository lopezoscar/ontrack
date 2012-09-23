package com.ontrack.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.EntryDocumentFile;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.users.User;

public class IssueEntryTest extends BaseTest{
	
	
	@Test
	public void test(){
		IssueEntry entry = new IssueEntry();
		entry.setComment("Un comentario");
		User user = em.find(User.class,1l);
		entry.setOwner(user);
		
		List<EntryDocumentFile> files = new ArrayList<EntryDocumentFile>();
		EntryDocumentFile file = new EntryDocumentFile();
		file.setIssueEntry(entry);
		file.setFileType("application/pdf");
		file.setName("Un archivo pdf");
		byte[] content = new byte[10];
		content[0]=1;
		file.setContent(content);
		file.setLength(content.length);
//		em.getTransaction().begin();
//		em.merge(file);
//		em.getTransaction().commit();
		entry.setFiles(files);
		
		
		persiste(entry);
		
	}

}
