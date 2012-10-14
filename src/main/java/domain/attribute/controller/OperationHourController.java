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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.OperationHour;
import domain.attribute.service.OperationHourService;

@Controller
@RequestMapping("data/operationhour")
public class OperationHourController {

	@Autowired
	OperationHourService operationHourService;
	@Autowired
	MappingJacksonHttpMessageConverter jsonConverter;

	@RequestMapping(value = "/get/{placeid}", method = RequestMethod.GET)
	@ResponseBody
	public List<OperationHour> getOperationHours(
			@PathVariable("placeid") long placeId) {
		return operationHourService.getOperationHours(placeId);
	}

	// ------------Create new Operation Hour ------------------------
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@ResponseBody
	public OperationHour newOperationHour() {
		return new OperationHour();
	}

	@RequestMapping(value = "/new/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void newOperationHour(@PathVariable("placeid") long placeId) {
		operationHourService.insert(placeId);
	}

	// ------------- Update Operation Hour --------------------------
	@RequestMapping(value = "/update/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void updateOperationHour(@PathVariable("placeid") long placeId, 
			@RequestBody OperationHour[] operationHours) {
		List<OperationHour> hours = Arrays.asList(operationHours);
		operationHourService.update(placeId, hours);
	}

	
}
