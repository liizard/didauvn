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
package domain.place.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import domain.place.dao.PlaceSettingDao;
import domain.place.model.PlaceManager;
import domain.place.service.PlaceSettingService;

@Service("placeSettingService")
public class PlaceSettingServiceImpl implements PlaceSettingService {
	@Autowired
	private PlaceSettingDao placeSettingDao;

	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and hasPermission(#placeId,'hasPlaceOwnerRight'))")
	@Override
	public PlaceManager addManager(String email, long placeId) {
		return placeSettingDao.addManager(email, placeId);
	}
	
	@Override
	public List<PlaceManager> getAllManagersByPlace(long placeId) {
		return placeSettingDao.getAllManagersByPlace(placeId);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and hasPermission(#placeId,'hasPlaceOwnerRight'))")
	@Override
	public void deleteManagers(List<PlaceManager> managers, long placeId) {
		placeSettingDao.deleteManagers(managers, placeId);
	}

}
