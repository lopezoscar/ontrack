package com.sappe.ontrack.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.HttpRequestHandler;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.gdata.client.Service.GDataRequest;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters.OAuthType;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;
import com.sappe.ontrack.model.issues.DocumentFile;
import com.sappe.ontrack.model.users.Member;


public class ContactsServlet  implements HttpRequestHandler{
	
	 public GoogleCredential createCredentialWithAccessTokenOnly(
		      HttpTransport transport, JsonFactory jsonFactory, TokenResponse tokenResponse) {
		    return new GoogleCredential().setFromTokenResponse(tokenResponse);
		  }
		/**
		 * Please provide a value for the CLIENT_ID constant before proceeding, set this up at https://code.google.com/apis/console/
		 */
		private static final String CLIENT_ID = "1086494138477-kpre74iookjfs4h47eu26a9r9qg5n1ku.apps.googleusercontent.com";
		/**
		 * Please provide a value for the CLIENT_SECRET constant before proceeding, set this up at https://code.google.com/apis/console/
		 */
		private static final String CLIENT_SECRET = "GU2fu6pZ5nb1bUlGtfAO1eIS";
	@Override
	public void handleRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();
		details.getSessionId();
		UserDetailsViewModel vm = (UserDetailsViewModel)user;
		String token = vm.getUser().getToken();
		GoogleAuthHelper helper = new GoogleAuthHelper();
		String xmlContacts = helper.getContacts(token);
		System.out.println(xmlContacts);
		List<Member> members = new ArrayList<Member>();
		
//		ContactsService service = new ContactsService("OScar");
//		
//		// Request the feed
//		URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
//		ContactFeed resultFeed;
//		try {
//			service.setUserCredentials("lopezoscar.job@gmail.com", "javaDeveloper1");
//			resultFeed = service.getFeed(feedUrl, ContactFeed.class);
//			// Print the results
//			System.out.println(resultFeed.getTitle().getPlainText());
//			
//			for (ContactEntry entry : resultFeed.getEntries()) {
//				Member.Builder memberBuild = new Member.Builder();
//				if (entry.hasName()) {
//					Name name = entry.getName();
//					if (name.hasFullName()) {
//						String fullNameToDisplay = name.getFullName().getValue();
//						memberBuild.name(fullNameToDisplay);
//					}else{
//						System.out.println("FullName not found");
//					}
//					System.out.println("Email addresses:");
//					for (Email email : entry.getEmailAddresses()) {
//						System.out.print(" " + email.getAddress());
//						memberBuild.email(email.getAddress());
//					}
//
//					Link photoLink = entry.getContactPhotoLink();
//					String photoLinkHref = photoLink.getHref();
//					//				DocumentFile image = downloadPhoto(service, entry);
//					//				memberBuild.image(image);
//
//					memberBuild.photoLink(photoLinkHref);
//					System.out.println("Photo Link: " + photoLinkHref);
//
//					Member member = memberBuild.build();
//					members.add(member);
//				}
//			}
//
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

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
