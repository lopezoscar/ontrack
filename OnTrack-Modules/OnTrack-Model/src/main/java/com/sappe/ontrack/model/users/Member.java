package com.sappe.ontrack.model.users;

import com.sappe.ontrack.model.issues.DocumentFile;

/**
 * @author Oscar Lopez
 *
 */
public class Member {
	
	private boolean selected;
	
	private String name;
	
	private String email;
	
	private String photoLink;
	
	
	private Member(Builder builder){
		this.name = builder.name;
		this.email = builder.email;
		this.photoLink = builder.photoLink;
	}
	
	public Member(){}
	
	public static final class Builder{
		private String name = "";
		
		private String email = "";
		
		private String photoLink = "";
		
		public Builder(){
			super();
		}
		
		public Builder name(String username){
			name = username; return this;
		}
		
		public Builder email(String emailVal){
			email = emailVal; return this;
		}
		
		public Builder photoLink(String photo){
			photoLink = photo; return this;
		}
		
		public Member build(){
			return new Member(this);
		}
		
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhotoLink() {
		return photoLink;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", email=" + email + ", photoLink="
				+ photoLink + "]";
	}

	
	

}
