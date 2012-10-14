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
package domain.attribute.service;

import java.util.List;

import domain.attribute.model.Feedback;
import domain.attribute.model.rq.FeedbackRq;
import domain.attribute.model.rq.GetMoreRq;

public interface FeedbackService {
	public List<Feedback> getFeedbackViews(long placeId);
	
	public List<Feedback> getMoreFeedbacks(GetMoreRq getMoreRq);
	
	public Feedback insert(FeedbackRq feedbackContent,long placeid);
	
	public boolean deleteFeedback(long id);
}
