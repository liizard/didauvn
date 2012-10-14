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
package domain.attribute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.District;
import domain.attribute.model.Street;
import domain.attribute.model.Ward;
import domain.attribute.service.LocationService;

@Controller
@RequestMapping("data/location")
public class LocationController {
	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/districts/{cityid}", method = RequestMethod.GET)
	@ResponseBody
	public List<District> getDistrictViews(@PathVariable("cityid") long cityId) {
		return locationService.getDistricts(cityId);
	}

	@RequestMapping(value = "/wards/{districtid}", method = RequestMethod.GET)
	@ResponseBody
	public List<Ward> getWardViews(@PathVariable("districtid") long districtId) {
		return locationService.getWards(districtId);
	}

	@RequestMapping(value = "/streets/{wardid}", method = RequestMethod.GET)
	@ResponseBody
	public List<Street> getStreetViews(@PathVariable("wardid") long wardId) {
		return locationService.getStreets(wardId);
	}

	
}
