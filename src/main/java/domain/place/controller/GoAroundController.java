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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.NewPlace;
import domain.attribute.model.NewsView;
import domain.attribute.model.OfferWeek;
import domain.place.service.GoAroundService;
import domain.place.service.PlaceService;

@Controller
@RequestMapping("data/goaround")
public class GoAroundController {

	@Autowired
	PlaceService placeService;

	@Autowired
	GoAroundService goAroundService;

	@ResponseBody
	@RequestMapping(value = "/getnews/{category}/{idx}", method = RequestMethod.GET)
	public List<NewsView> getNewsPerPage(
			@PathVariable("category") int category, @PathVariable("idx") int idx) {
		return goAroundService.getNewsByCategory(category, idx);
	}

	@ResponseBody
	@RequestMapping(value = "/getoffers/{category}/{idx}", method = RequestMethod.GET)
	public List<OfferWeek> getOffersPerPage(
			@PathVariable("category") int category, @PathVariable("idx") int idx) {
		return goAroundService.getOfferByCategory(category, idx);
	}

	@ResponseBody
	@RequestMapping(value = "/getnewplaces/{index}", method = RequestMethod.POST)
	public List<NewPlace> getNewPlace(@PathVariable("index") int idx,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		List<NewPlace> newPlaces = new ArrayList<NewPlace>();
		return newPlaces;
	}

}
