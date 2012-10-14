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
package domain.user.dao;

import java.util.List;

import core.enumeration.LanguageEnum;

public interface UserSettingDao {

	public String getLang(long id);

	public void updateLang(long id, LanguageEnum lang);

	public List<Long> getPlaceOwners(long userId);

	public List<Long> getPlaceManagers(long userId);

	List<Long> getPlaceWows(long userId);

}