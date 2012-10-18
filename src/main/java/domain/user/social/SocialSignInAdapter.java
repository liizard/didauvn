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
/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package domain.user.social;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import domain.user.dao.UserDao;
import domain.user.model.SecureUser;
import domain.user.model.User;
import domain.user.model.UserFB;

@Service
public class SocialSignInAdapter implements SignInAdapter {
	private static final Logger RUNTIME_LOGGER = Logger
			.getLogger("runtimeLogger");
	private static final Logger LOGGER = Logger.getLogger("mainLogger");

	@Autowired
	private UserDao userDao;
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Override
	public String signIn(String localUserId, Connection<?> connection,
			NativeWebRequest request) {
		try {
			// Get user with the social ID: localUserId
			UserFB userFB = userDao.findFBById(Long.parseLong(localUserId));

			if (userFB != null && userFB.getStatus() == 1) {
				User user = new User();

				Facebook facebook = usersConnectionRepository
						.createConnectionRepository(localUserId)
						.findPrimaryConnection(Facebook.class).getApi();
				FacebookProfile profile = facebook.userOperations()
						.getUserProfile();

				user.setUid(userFB.getUid());
				user.setName(profile.getName());
				if (profile.getBirthday() != null) {
					user.setBirthday(profile.getBirthday());
				}
				if (profile.getGender() != null) {
					user.setGender(profile.getGender());
				}
				if (profile.getHometown() != null) {
					user.setHomeTown(profile.getHometown().getName());
				}
				if (profile.getRelationshipStatus() != null) {
					user.setrStatus(profile.getRelationshipStatus());
				}

				try {
					userDao.update(user);
				} catch (ParseException e) {
					LOGGER.error("Facebook Get Info Error");
					RUNTIME_LOGGER.error("Facebook Get Info Error", e);
				}
				List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
				auths.add(new SimpleGrantedAuthority("ROLE_USER"));
				if (userDao.isAdmin(user.getUid())) {
					auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				}

				SecureUser secureUser = new SecureUser(userFB, auths);
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						secureUser, secureUser.getPassword(),
						secureUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(
						authentication);
				// Return page when login process successfully
				return SocialConfig.SOCIAL_CONNECT_SUCCESS_URL;
			} else {
				// Return homepage
				return null;
			}
		} catch (RuntimeException e) {
			LOGGER.error("Facebook Get Info Error");
			RUNTIME_LOGGER.error("Facebook Get Info Error", e);
			throw e;
		}
	}
}
