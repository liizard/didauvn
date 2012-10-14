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
package domain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/component")
public class ComponentController {
	private final static String VIEW_BASE = "comp";

	@RequestMapping(value = "/upload/{placeid}", method = RequestMethod.GET)
	public String upload(ModelMap model, @PathVariable("placeid") long placeId) {
		model.addAttribute("placeId", placeId);
		return VIEW_BASE + "/upload";
	}
	
	@RequestMapping(value = "/closepopup", method = RequestMethod.GET)
	public String close() {
		return VIEW_BASE + "/closepopup";
	}
}
