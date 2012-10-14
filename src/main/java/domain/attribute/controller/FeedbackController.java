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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.Feedback;
import domain.attribute.model.rq.FeedbackRq;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.service.FeedbackService;

@Controller
@RequestMapping("data/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/getmore", method = RequestMethod.POST)
	@ResponseBody
	public List<Feedback> getMoreFeedbacks(@RequestBody GetMoreRq getMoreRq) {
		return feedbackService.getMoreFeedbacks(getMoreRq);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@ResponseBody
	public FeedbackRq getFeedbackView() {
		return new FeedbackRq();
	}

	@RequestMapping(value = "/new/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback getFeedbackView(@RequestBody FeedbackRq feedbackContent,
			@PathVariable("placeid") long placeId) {
		return feedbackService.insert(feedbackContent, placeId);
	}

	@RequestMapping(value = "/delete/{feedbackid}", method = RequestMethod.GET)
	@ResponseBody
	public String getFeedbackView(@PathVariable("feedbackid") long id) {
		if (feedbackService.deleteFeedback(id))
			return "success";
		else
			return "fail";
	}

}
