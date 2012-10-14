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

import core.exception.DdException;

import domain.place.model.PlaceDetail;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceRq;
import domain.place.model.rq.PlaceSearchRq;

public interface PlaceService {
	public long insertPlace(PlaceRq place) throws DdException;

	public void updatePlace(PlaceRq place) throws DdException;

	public PlaceRq getPlace(long id);

	public PlaceDetail getPlaceDetail(long id);

	public List<PlaceGeneral> searchPlace(PlaceSearchRq searchRequest);

}
