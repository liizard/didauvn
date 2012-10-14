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

import java.util.List;

public interface UserSettingService {

	public String getLang();

	public void setLang(String lang);

	public String getDbLang();

	List<Long> getPlaceOwners();

	List<Long> getPlaceManagers();

	List<Long> getPlaceWows();

}