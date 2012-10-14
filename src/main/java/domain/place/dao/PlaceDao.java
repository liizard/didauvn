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
package domain.place.dao;

import java.util.List;

import domain.place.model.PlaceDetail;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceRq;

public interface PlaceDao {
	public long insertPlace(final PlaceRq place, final long userId);

	public void updatePlace(PlaceRq place);

	public PlaceRq getPlace(long id);

	public PlaceDetail getPlaceDetail(long id, String locale);

	public List<PlaceGeneral> searchPlace(String locale, String tagIds,
			String locationQuery, int from, int count);

	public List<PlaceGeneral> getPlaceOwner(String locale, long userId);

	public List<PlaceGeneral> getPlaceManager(String locale,String criteria);
	
	public void updateAvatar(long placeId, long imageId);
	
	public List<PlaceGeneral> getPlaceWow(String locale,String criteria);
	
	public void updateViewCount(long placeId);
	
	public int getWowCount(long placeId);
}
