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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.exception.DdException;

import domain.place.model.PlaceDetail;
import domain.place.model.rq.PlaceRq;
import domain.place.service.PlaceService;

@Controller
@RequestMapping("data/place")
public class PlaceController {
	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = "/get/{placeid}", method = RequestMethod.GET)
	@ResponseBody
	public PlaceDetail getPlace(@PathVariable("placeid") long placeId) {
		return placeService.getPlaceDetail(placeId);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@ResponseBody
	public PlaceRq newPlace() {
		return new PlaceRq();
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public long newPlace(@RequestBody PlaceRq place) throws DdException {
		return placeService.insertPlace(place);
	}

	@RequestMapping(value = "/update/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public PlaceRq updatePlace(@PathVariable("placeid") long placeId) {
		return placeService.getPlace(placeId);
	}

	@RequestMapping(value = "/save/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void savePlace(@RequestBody PlaceRq place) throws DdException{
		placeService.updatePlace(place);
	}

}
