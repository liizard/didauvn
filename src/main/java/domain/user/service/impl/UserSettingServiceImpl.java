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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import core.context.ContextHolder;
import core.enumeration.LanguageEnum;
import domain.user.dao.UserSettingDao;
import domain.user.model.SecureUser;
import domain.user.service.UserSettingService;

@Service("userSettingService")
public class UserSettingServiceImpl implements UserSettingService {
	@Autowired
	UserSettingDao userSettingDao;

	@Override
	public String getLang() {
		return ContextHolder.getInstance().getCurrentLang().toString()
				.toLowerCase();
	}

	@Override
	public String getDbLang() {
		SecureUser user = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user != null) {
			String lang = userSettingDao.getLang(user.getUser().getUid());
			return lang.toLowerCase();
		} else {
			return null;
		}
	}

	@Override
	public void setLang(String lang) {
		LanguageEnum languageEnum = LanguageEnum.valueOf(lang.toUpperCase());
		if (lang != null) {
			SecureUser user = (SecureUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			if (user != null) {
				userSettingDao
						.updateLang(user.getUser().getUid(), languageEnum);
			}
			ContextHolder.getInstance().setCurrentLang(languageEnum);
		}
	}

	@Override
	public List<Long> getPlaceOwners() {
		SecureUser user = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user != null) {
			return userSettingDao.getPlaceOwners(user.getUser().getUid());
		}
		return null;
	}

	@Override
	public List<Long> getPlaceManagers() {
		SecureUser user = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user != null) {
			return userSettingDao.getPlaceManagers(user.getUser().getUid());
		}
		return null;
	}

	@Override
	public List<Long> getPlaceWows() {
		SecureUser user = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user != null) {
			return userSettingDao.getPlaceWows(user.getUser().getUid());
		}
		return null;
	}

}
