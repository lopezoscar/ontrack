package com.sappe.ontrack.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;

public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userSrv;

	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException, DataAccessException {
//		GoogleAuthHelper helper = new GoogleAuthHelper();
//		helper.getUserInfoJson(authCode);
		
		
		
		User user = userSrv.userByUserName(username);
		if(user == null){
			OpenIDAuthenticationToken token = (OpenIDAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
			if(token !=null){
				List<OpenIDAttribute> attributes = token.getAttributes();
				throw new UsernameNotFoundException("El usuario"+ attributes.get(0) +"no se encuentra registrado en la base de datos");
			}else{
				throw new UsernameNotFoundException("No se pudo obtener el usuario ni el token");
			}
		}
		UserDetailsViewModel vm = new UserDetailsViewModel();
		vm.setUser(user);
		return vm;
	}

}
