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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.place.model.PlaceManager;
import domain.place.service.PlaceSettingService;

@Controller
@RequestMapping("data/place/setting")
public class PlaceSettingController {
	@Autowired
	private PlaceSettingService placeSettingService;

	@RequestMapping(value = "/manager/new", method = RequestMethod.GET)
	@ResponseBody
	public PlaceManager getManagerTemplate() {
		return new PlaceManager();
	}

	@RequestMapping(value = "/manager/new/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public PlaceManager addManager(@RequestBody PlaceManager manager,
			@PathVariable("placeid") long placeId) {
		return placeSettingService.addManager(manager.getEmail(), placeId);
	}

	@RequestMapping(value = "/manager/delete/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void deleteManagers(@RequestBody PlaceManager[] deleteManagers,
			@PathVariable("placeid") long placeId) {
		List<PlaceManager> managers = Arrays.asList(deleteManagers);
		placeSettingService.deleteManagers(managers, placeId);
	}

	@RequestMapping(value = "/manager/get/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public List<PlaceManager> getAllManagersbyPlace(
			@PathVariable("placeid") long placeId) {
		return placeSettingService.getAllManagersByPlace(placeId);
	}
}
