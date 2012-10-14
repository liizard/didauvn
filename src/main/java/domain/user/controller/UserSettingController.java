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
package domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.user.service.UserSettingService;

@Controller
@RequestMapping("data/user/setting")
public class UserSettingController {
	@Autowired
	private UserSettingService userSettingService;

	@RequestMapping(value = "/lang/get", method = RequestMethod.GET)
	@ResponseBody
	public String getCurrentUserLang() {
		try {
			return userSettingService.getLang();
		} catch (Exception e) {
			e.printStackTrace();
			return new String("en");
		}
	}

	@RequestMapping(value = "/lang/update/{lang}", method = RequestMethod.POST)
	@ResponseBody
	public void updateCurrentUserLang(@PathVariable("lang") String lang) {
		try {
			userSettingService.setLang(lang);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
