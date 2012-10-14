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
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import core.context.ContextHolder;
import domain.attribute.dao.LocationDao;
import domain.attribute.dao.PlaceTagDao;
import domain.place.dao.PlaceDao;
import domain.place.model.LocationSearch;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceSearchRq;
import domain.place.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private PlaceTagDao placeTagDao;
	

	@Override
	public List<PlaceGeneral> searchPlace(PlaceSearchRq searchRequest) {
		// Get list tag id for query
		String tagIds = "";
		for (int i = 0; i < searchRequest.getTags().length; i++) {
			tagIds += searchRequest.getTags()[i].getId() + ",";
		}
		// update list tag's rank
		placeTagDao.updateRank(searchRequest.getTags());
		
		// Remove last comma
		if (tagIds.length() > 0) {
			tagIds = tagIds.substring(0, tagIds.length() - 1);
		}

		String locationQuery = "";
		if (searchRequest.getStreetId() > 0) {
			locationQuery = "StreetId=" + searchRequest.getStreetId();
		} else if (searchRequest.getWardId() > 0) {
			locationQuery = "WardId=" + searchRequest.getWardId();
		} else if (searchRequest.getDistrictId() > 0) {
			locationQuery = "DistrictId=" + searchRequest.getDistrictId();
		} else {
			locationQuery = "CityId=" + searchRequest.getCityId();
		}

		if (searchRequest.getPage() < 1)
			searchRequest.setPage(1);

		return placeDao.searchPlace(ContextHolder.getInstance()
				.getCurrentLang().toString(), tagIds, locationQuery,
				(searchRequest.getPage() - 1)
						* WebConstant.SEARCH_RESULT_PER_PAGE,
				WebConstant.SEARCH_RESULT_PER_PAGE + 1);
	}

	@Override
	public List<LocationSearch> searchLocation(String location) {
		return locationDao.searchLocation(ContextHolder.getInstance()
				.getCurrentLang().toString(), location);
	}

}
