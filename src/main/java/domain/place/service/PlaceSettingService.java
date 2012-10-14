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
package domain.place.service;

import java.util.List;

import domain.place.model.PlaceManager;

public interface PlaceSettingService {
	public PlaceManager addManager(String email, long placeId);

	public void deleteManagers(List<PlaceManager> managers, long placeId);

	public List<PlaceManager> getAllManagersByPlace(long placeId);
}
