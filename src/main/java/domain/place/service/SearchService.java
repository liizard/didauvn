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

import domain.place.model.LocationSearch;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceSearchRq;

public interface SearchService {
	
	public List<PlaceGeneral> searchPlace(PlaceSearchRq searchRequest);
	
	public List<LocationSearch> searchLocation(String location);
}
