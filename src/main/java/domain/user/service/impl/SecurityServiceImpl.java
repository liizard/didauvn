/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import domain.user.dao.UserDao;
import domain.user.model.SecureUser;
import domain.user.model.User;
import domain.user.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements UserDetailsService, SecurityService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User user = userDao.getUserByEmail(email);
		if (user != null && user.getStatus() == 1) {
			// Add Roles attached with this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));

			if (userDao.isAdmin(user.getUid())) {
				authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}

			SecureUser secureUser = new SecureUser(user, authList);
			return secureUser;
		}
		return null;
	}

}
