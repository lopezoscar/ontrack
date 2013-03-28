package com.sappe.ontrack.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;

public class SpringSecurityUtil {
	
	public static final Collection<GrantedAuthority> getAuthorities(User user){
		
		final String[] authorities = new String[user.getRoles().size()];
		if(user != null){
//			if(user.getPermissions() != null){
//				for (int permission = 0; permission < user.getPermissions().size() ; permission++) {
//					authorities[permission] = user.getPermissions().get(permission).getAcronym();
//				}
//			}
			if(user.getRoles()!= null){
				for (Role userRole : user.getRoles()) {
					for (int permission = 0; permission < userRole.getPermissions().size(); permission++) {
						authorities[permission] = userRole.getPermissions().get(permission).getAcronym();
					}
				}
			}
		}
		
		List<GrantedAuthority> permissions = AuthorityUtils.createAuthorityList(authorities);
		return permissions;
	} 
	
}
