package com.sappe.ontrack.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gdata.client.Service.GDataRequest;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;
import com.sappe.ontrack.model.issues.DocumentFile;
import com.sappe.ontrack.model.users.Member;

public class ContactServiceTest {

	@Test
	public void test(){
		try {
			printAllContacts("lopezoscar.job@gmail.com", "javaDeveloper1");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printAllContacts(String userName,String password)throws ServiceException, IOException {
		ContactsService service = new ContactsService("OScar");
		if (userName == null || password == null) {
			return;
		}
		service.setUserCredentials(userName, password);


		List<Member> members = new ArrayList<Member>();	    
		// Request the feed
		URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		ContactFeed resultFeed = service.getFeed(feedUrl, ContactFeed.class);
		// Print the results
		System.out.println(resultFeed.getTitle().getPlainText());
		for (ContactEntry entry : resultFeed.getEntries()) {
			Member.Builder memberBuild = new Member.Builder();
			if (entry.hasName()) {
				Name name = entry.getName();
				if (name.hasFullName()) {
					String fullNameToDisplay = name.getFullName().getValue();
					memberBuild.name(fullNameToDisplay);
					if (name.getFullName().hasYomi()) {
						fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";

					}
					System.out.println("\\\t\\\t" + fullNameToDisplay);
				} else {
					System.out.println("\\\t\\\t (no full name found)");
				}
				if (name.hasNamePrefix()) {
					System.out.println("\\\t\\\t" + name.getNamePrefix().getValue());
				} else {
					System.out.println("\\\t\\\t (no name prefix found)");
				}
				if (name.hasGivenName()) {
					String givenNameToDisplay = name.getGivenName().getValue();
					if (name.getGivenName().hasYomi()) {
						givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
					}
					System.out.println("\\\t\\\t" + givenNameToDisplay);
				} else {
					System.out.println("\\\t\\\t (no given name found)");
				}
				if (name.hasAdditionalName()) {
					String additionalNameToDisplay = name.getAdditionalName().getValue();
					if (name.getAdditionalName().hasYomi()) {
						additionalNameToDisplay += " (" + name.getAdditionalName().getYomi() + ")";
					}
					System.out.println("\\\t\\\t" + additionalNameToDisplay);
				} else {
					System.out.println("\\\t\\\t (no additional name found)");
				}
				if (name.hasFamilyName()) {
					String familyNameToDisplay = name.getFamilyName().getValue();
					if (name.getFamilyName().hasYomi()) {
						familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";
					}
					System.out.println("\\\t\\\t" + familyNameToDisplay);
				} else {
					System.out.println("\\\t\\\t (no family name found)");
				}
				if (name.hasNameSuffix()) {
					System.out.println("\\\t\\\t" + name.getNameSuffix().getValue());
				} else {
					System.out.println("\\\t\\\t (no name suffix found)");
				}
			} else {
				System.out.println("\t (no name found)");
			}
			System.out.println("Email addresses:");
			for (Email email : entry.getEmailAddresses()) {
				System.out.print(" " + email.getAddress());
				memberBuild.email(email.getAddress());
				if (email.getRel() != null) {
					System.out.print(" rel:" + email.getRel());
				}
				if (email.getLabel() != null) {
					System.out.print(" label:" + email.getLabel());
				}
				if (email.getPrimary()) {
					System.out.print(" (primary) ");
				}
				System.out.print("\n");
			}
			System.out.println("IM addresses:");
			for (Im im : entry.getImAddresses()) {
				System.out.print(" " + im.getAddress());
				if (im.getLabel() != null) {
					System.out.print(" label:" + im.getLabel());
				}
				if (im.getRel() != null) {
					System.out.print(" rel:" + im.getRel());
				}
				if (im.getProtocol() != null) {
					System.out.print(" protocol:" + im.getProtocol());
				}
				if (im.getPrimary()) {
					System.out.print(" (primary) ");
				}
				System.out.print("\n");
			}
			System.out.println("Groups:");
			for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
				String groupHref = group.getHref();
				System.out.println("  Id: " + groupHref);
			}
			System.out.println("Extended Properties:");
			for (ExtendedProperty property : entry.getExtendedProperties()) {
				if (property.getValue() != null) {
					System.out.println("  " + property.getName() + "(value) = " +
							property.getValue());
				} else if (property.getXmlBlob() != null) {
					System.out.println("  " + property.getName() + "(xmlBlob)= " +
							property.getXmlBlob().getBlob());
				}
			}
			Link photoLink = entry.getContactPhotoLink();
			String photoLinkHref = photoLink.getHref();
			System.out.println("Photo Link: " + photoLinkHref);
			if(photoLink != null){
				DocumentFile image = downloadPhoto(service,entry);
//				memberBuild.image(image);
			}
			if (photoLink.getEtag() != null) {

				memberBuild.photoLink(photoLinkHref);

				System.out.println("Contact Photo's ETag: " + photoLink.getEtag());
			}
			System.out.println("Contact's ETag: " + entry.getEtag());

			Member member = memberBuild.build();
			members.add(member);
		}
		
		
		
		for (Member member : members) {
			System.out.println(member.getName());
			System.out.println(member.getEmail());
		}
	}

	public DocumentFile downloadPhoto(ContactsService service, ContactEntry entry)
	throws ServiceException, IOException {
		//		ContactEntry entry = service.getEntry(contactURL,  ContactEntry.class);
		DocumentFile image = new DocumentFile();
		Link photoLink = entry.getContactPhotoLink();
		if (photoLink != null) {
			GDataRequest req = service.createLinkQueryRequest(photoLink);
			try{
				req.execute();
				InputStream in = req.getResponseStream();;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				image.setFileType("image/pjpeg");
				byte[] buffer = new byte[4096];
				int read = 0;
				while (true) {
					if ((read = in.read(buffer)) != -1) {
						out.write(buffer, 0, read);
					} else {
						break;
					}
				}
				
				byte[] content = out.toByteArray();
				image.setContent(content);
				image.setLength(content.length);
				image.setName(entry.getName().getFullName().getValue()+".jpg");
				
			}catch(ResourceNotFoundException rne){
				System.out.println("No hay foto");
			}

		}
		
		return image;
	}


}
