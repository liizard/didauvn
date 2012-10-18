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
package domain.user.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import core.exception.DdException;
import domain.place.model.PlaceGeneral;
import domain.user.model.Password;
import domain.user.model.User;
import domain.user.model.UserGeneral;
import domain.user.model.UserSession;

public interface UserService {
	public UserGeneral getUser(long userId);

	public long insert(User user, WebRequest request) throws DdException,
			IOException;

	public String convertDateFormat(String from, String to, String src)
			throws ParseException;

	public boolean checkEmail(String email);

	public User returnOAuthUser(WebRequest request) throws ParseException;

	public UserSession getCurrentUser();

	public List<PlaceGeneral> getPlaceOwner();

	public List<PlaceGeneral> getPlaceManager();

//	public long insertImage(MultipartFile multipartFile) throws Exception;
//
//	public long getImage(long userId);

	public boolean isSuccessfulActivation(long userId, String token);

	public List<PlaceGeneral> getPlaceWow();

	public boolean isAdmin(long id);
}
