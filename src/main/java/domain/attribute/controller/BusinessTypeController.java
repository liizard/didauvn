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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.BusinessType;
import domain.attribute.service.BusinessTypeService;

@Controller
@RequestMapping("data/businesstype")
public class BusinessTypeController {
	@Autowired
	private BusinessTypeService businessTypeService;

	@RequestMapping(value = "/gets", method = RequestMethod.GET)
	@ResponseBody
	public List<BusinessType> getBusinessTypes() {
		return businessTypeService.getBusinessTypes();
	}
}
