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
package domain.attribute.dao;

import java.util.List;

import domain.attribute.model.Feedback;

public interface FeedbackDao {
	public List<Feedback> getFeedbackViews(long placeId);

	public long insert(final Feedback feedbackView, long placeId);

	public boolean checkPlace(long id);

	public boolean deleteFeedback(long id, long userId);

	public List<Feedback> getMoreFeedbacks(long placeId, long lastId, int count);
}
