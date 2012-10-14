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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;

@Controller
@RequestMapping("data/paging")
public class PagingController {
	@ResponseBody
	@RequestMapping(value = "/getmorerq", method = RequestMethod.POST)
	public GetMoreRq getMoreRq() {
		return new GetMoreRq();
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagerq", method = RequestMethod.POST)
	public PageRq getPageRq() {
		return new PageRq();
	}
}
