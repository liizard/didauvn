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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import core.context.ContextHolder;
import core.exception.DdException;
import core.job.SendMailJob;
import core.job.executor.AsyncJobExecutor;
import core.mail.MailService;
import core.mail.TemplateEnum;
import core.util.SecurityUtil;
import domain.place.dao.PlaceDao;
import domain.place.model.PlaceGeneral;
import domain.user.dao.UserDao;
import domain.user.model.SecureUser;
import domain.user.model.User;
import domain.user.model.UserGeneral;
import domain.user.model.UserSession;
import domain.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private MailService mailService;

	@Autowired
	private AsyncJobExecutor executor;

	@Override
	public UserGeneral getUser(long userId) {
		return userDao.findUserViewById(userId);
	}

	private void validateUpdate(User user) throws DdException {
		if (user.getName().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.NAME_EMPTY_CODE, User.NAME_EMPTY);
		}
		if (user.getName().length() > 45) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.NAME_TOO_LONG_CODE, User.NAME_TOO_LONG);
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.parse(user.getBirthday());
		} catch (ParseException ex) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.BIRTHDAY_INVALID_CODE, User.BIRTHDAY_INVALID);
		}
	}

	private void validateRegister(User user) throws DdException {
		if (user.getEmail().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.EMAIL_EMPTY_CODE, User.EMAIL_EMPTY);
		}
		if (!this.checkEmail(user.getEmail())) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.EMAIL_INVALID_CODE, User.EMAIL_INVALID);
		}

		validateUpdate(user);

		if (user.getBirthday().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.BIRTHDAY_EMPTY_CODE, User.BIRTHDAY_EMPTY);
		}
		if (user.getPassword().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.PASSWORD_EMPTY_CODE, User.PASSWORD_EMPTY);
		}
		if (user.getPassword().length() < 5) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					User.PASSWORD_TOO_SHORT_CODE, User.PASSWORD_TOO_SHORT);
		}
	}

	@Override
	public long insert(User user, WebRequest request) throws DdException,
			IOException {
		long uid = 0;

		validateRegister(user);

		uid = userDao.insert(user);

		// BEGIN Mail Activation
		HashMap<String, Object> data = new HashMap<String, Object>();
		String locale = ContextHolder.getInstance().getCurrentLang().getLang();
		String token = RandomStringUtils.randomAlphanumeric(11);
		userDao.insertToken(uid, token);
		data.put("name", user.getName());
		data.put("uid", uid);
		data.put("token", token);

		SendMailJob job = new SendMailJob(mailService, user.getEmail(),
				user.getEmail(), locale, data, TemplateEnum.CONFIRM_REGISTER);

		executor.execute(job);

		/*
		 * mailService.send(user.getEmail(), user.getEmail(), locale, data,
		 * TemplateEnum.CONFIRM_REGISTER);
		 */

		// END Mail Activation
		String md5Pass = SecurityUtil.getMd5String(user.getPassword());
		userDao.insertPassword(uid, md5Pass);

		// Get Avatar from fb
		// if (user.isFacebookUser()) {
		// Connection<?> connection = ProviderSignInUtils
		// .getConnection(request);
		// Connection<Facebook> fbConnection = (Connection<Facebook>)
		// connection;
		// long imageId = System.currentTimeMillis();
		// FileUtils
		// .writeByteArrayToFile(
		// new File(SystemConstant.PATH_USER_AVATAR + imageId
		// + ".jpg"),
		// fbConnection.getApi().userOperations()
		// .getUserProfileImage(ImageType.SQUARE));
		// userDao.updateImage(ContextHolder.getInstance().getCurrentUserId(),
		// imageId);
		// ProviderSignInUtils.handlePostSignUp(String.valueOf(uid), request);
		// }

		return uid;
	}

	@Override
	public String convertDateFormat(String from, String to, String src)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(from);
		Date date = df.parse(src);
		df = new SimpleDateFormat(to);
		return df.format(date);
	}

	@Override
	public boolean checkEmail(String email) {
		String patternEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(patternEmail);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return userDao.checkEmail(email);
		} else
			return false;
	}
	
	@Override
	public User returnOAuthUser(WebRequest request) throws ParseException {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			if (connection.getKey().getProviderId()
					.equalsIgnoreCase("facebook")) {
				@SuppressWarnings("unchecked")
				Connection<Facebook> fbConnection = (Connection<Facebook>) connection;
				FacebookProfile facebookProfile = fbConnection.getApi()
						.userOperations().getUserProfile();
				User user = new User(0, facebookProfile.getEmail(), "",
						facebookProfile.getName(), this.convertDateFormat(
								"MM/dd/yyyy", "dd/MM/yyyy",
								facebookProfile.getBirthday()), 0,
						facebookProfile.getGender(), null,
						fbConnection.getImageUrl(), "0", true);
				return user;
			} else
				return new User();
		} else {
			User user = new User();
			user.setGender("male");
			return user;
		}
	}

	@Override
	public UserSession getCurrentUser() {
		if (!SecurityContextHolder.getContext().getAuthentication().getName()
				.equalsIgnoreCase("anonymousUser")) {
			SecureUser secureUser = (SecureUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			return new UserSession(secureUser.getUser(), ContextHolder
					.getInstance().getUserPlaceOwner(), ContextHolder
					.getInstance().getUserPlaceManager(), ContextHolder
					.getInstance().getUserPlaceWow(), ContextHolder
					.getInstance().isAdmin());
		}
		return new UserSession();
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public List<PlaceGeneral> getPlaceOwner() {
		SecureUser secureUser = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return placeDao.getPlaceOwner(ContextHolder.getInstance()
				.getCurrentLang().toString(), secureUser.getUser().getUid());
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public List<PlaceGeneral> getPlaceManager() {
		List<Long> placeIds = ContextHolder.getInstance().getUserPlaceManager();
		if (placeIds.isEmpty()) {
			return null;
		}
		// get IN Clause (...)
		StringBuilder criteria = new StringBuilder();
		Iterator<Long> i = placeIds.iterator();
		if (i.hasNext()) {
			criteria.append(i.next());
			while (i.hasNext()) {
				criteria.append(",").append(i.next());
			}
		}
		return placeDao.getPlaceManager(ContextHolder.getInstance()
				.getCurrentLang().toString(), criteria.toString());
	}

	// @Override
	// @PreAuthorize("hasRole('ROLE_USER')")
	// public long insertImage(MultipartFile multipartFile) throws Exception {
	// try {
	// // Get image and delete if not default image
	// long curImageId = getImage(ContextHolder.getInstance()
	// .getCurrentUserId());
	// if (curImageId > 0) {
	// imageUtil.deleteImg(curImageId + ".jpg");
	// }
	// long imageId = System.currentTimeMillis();
	// imageUtil.createThumbnail(multipartFile, imageId + ".jpg", 45);
	// userDao.updateImage(ContextHolder.getInstance().getCurrentUserId(),
	// imageId);
	// ContextHolder.getInstance().getCurrentUser().getUser()
	// .setAvatar(String.valueOf(imageId));
	// return imageId;
	// } catch (DdException e) {
	// if (e.getCode().equalsIgnoreCase(ImageUtil.FILE_SIZE_LIMIT_CODE)) {
	// throw new DdException(DdException.VALIDATION_EXCEPTION,
	// User.AVATAR_SIZE_LIMIT_CODE, User.AVATAR_SIZE_LIMIT, e);
	//
	// } else if (e.getCode().equalsIgnoreCase(
	// ImageUtil.FILE_TYPE_NOT_ALLOW_CODE)) {
	// throw new DdException(DdException.VALIDATION_EXCEPTION,
	// User.AVATAR_TYPE_NOT_ALLOW_CODE,
	// User.AVATAR_TYPE_NOT_ALLOW, e);
	// } else {
	// throw e;
	// }
	// } catch (Exception e) {
	// throw e;
	// }
	// }
	//
	// public long getImage(long userId) {
	// return userDao.getImage(userId);
	// }

	@Override
	public boolean isSuccessfulActivation(long userId, String token) {
		if (userDao.isValidActivation(userId, token)) {
			userDao.activateUser(userId);
			userDao.deleteInvalidActivation(userId);
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<PlaceGeneral> getPlaceWow() {
		List<Long> placeIds = ContextHolder.getInstance().getUserPlaceWow();
		if (placeIds.isEmpty()) {
			return null;
		}
		// get IN Clause (...)
		StringBuilder criteria = new StringBuilder();
		Iterator<Long> i = placeIds.iterator();
		if (i.hasNext()) {
			criteria.append(i.next());
			while (i.hasNext()) {
				criteria.append(",").append(i.next());
			}
		}
		return placeDao.getPlaceWow(ContextHolder.getInstance()
				.getCurrentLang().toString(), criteria.toString());
	}

	@Override
	public boolean isAdmin(long id) {
		return userDao.isAdmin(id);
	}
}
