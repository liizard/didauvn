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

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import core.constant.SystemConstant;
import domain.user.dao.UserDao;
import domain.user.model.SecureUser;
import domain.user.model.User;

/**
 * Spring Social Configuration.
 * 
 * @author Craig Walls
 */
@Configuration
@ImportResource("classpath:META-INF/spring/applicationContext*.xml")
public class SocialConfig {
	public final static String SOCIAL_CONNECT_VIEW = "";	// "user/userconnect";
	public final static String SOCIAL_CONNECT_URL = SystemConstant.DOMAIN
			+ "/user/#/connect";
	public final static String SOCIAL_CONNECT_SUCCESS_URL = SystemConstant.DOMAIN
			+ "/user/#/place";

	// Facebook constant
	public final static String FB_APP_ID = "439021562792734";
	public final static String FB_APP_SECRET = "2e509bb56e437095c53b0c21e83e88a7";

	@Inject
	private DataSource dataSource;
	
	@Inject
	private UserDao userDao;

	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(FB_APP_ID,
				FB_APP_SECRET));
		return registry;
	}

	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
				dataSource, connectionFactoryLocator(), textEncryptor());
		repository.setConnectionSignUp(new AccountConnectionSignUp(userDao));
		return repository;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			throw new AccessDeniedException("No user signed in");
		}
		User user = ((SecureUser) authentication.getPrincipal()).getUser();
		return usersConnectionRepository().createConnectionRepository(
				String.valueOf(user.getUid()));
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		Connection<Facebook> facebook = connectionRepository()
				.findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}

}