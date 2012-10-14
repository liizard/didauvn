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
package domain.place.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.context.ContextHolder;

import domain.place.model.LocationSearch;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.LocationSearchRq;
import domain.place.model.rq.PlaceSearchRq;
import domain.place.service.SearchService;

@Controller
@RequestMapping("data/search")
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/place", method = RequestMethod.POST)
	@ResponseBody
	public List<PlaceGeneral> getPlaceSearch(
			@RequestBody PlaceSearchRq placeSearchRq) {
		return searchService.searchPlace(placeSearchRq);
	}

	// Save place search request in contextHolder
	@RequestMapping(value = "/place/save", method = RequestMethod.POST)
	@ResponseBody
	public void savePlaceSearch(@RequestBody PlaceSearchRq placeSearchRq) {
		ContextHolder.getInstance().setPlaceSearchRq(placeSearchRq);
	}

	// Get place search request in contextHolder
	@RequestMapping(value = "/place/get", method = RequestMethod.GET)
	@ResponseBody
	public PlaceSearchRq getPlaceSearch() {
		return ContextHolder.getInstance().getPlaceSearchRq();
	}

	@RequestMapping(value = "/placesearchrequest", method = RequestMethod.GET)
	@ResponseBody
	public PlaceSearchRq getPlaceSearchRequest(Locale locale) {
		PlaceSearchRq searchRequest = new PlaceSearchRq();
		return searchRequest;
	}

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	@ResponseBody
	public List<LocationSearch> searchLocation(
			@RequestBody LocationSearchRq locationSearchRequest) {
		return searchService
				.searchLocation(locationSearchRequest.getLocation());
	}
}
