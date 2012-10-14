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
package domain.attribute.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import core.job.UpdateNotifyJob;
import core.job.executor.AsyncJobExecutor;
import domain.attribute.dao.FeedbackDao;
import domain.attribute.dao.MessageDao;
import domain.attribute.model.Feedback;
import domain.attribute.model.MessageEnum;
import domain.attribute.model.rq.FeedbackRq;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.service.FeedbackService;
import domain.user.model.SecureUser;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private AsyncJobExecutor asyncJobExecutor;
	

	@Override
	public List<Feedback> getFeedbackViews(long placeId) {
		return feedbackDao.getFeedbackViews(placeId);
	}

	@Override
	public List<Feedback> getMoreFeedbacks(GetMoreRq getMoreRq) {
		return feedbackDao.getMoreFeedbacks(getMoreRq.getPlaceId(), getMoreRq.getLastId(),
				WebConstant.FEED_BACK_PER_PAGE + 1);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Feedback insert(FeedbackRq feedbackContent, long placeid) {
		if (feedbackDao.checkPlace(placeid)) {
			SecureUser user = (SecureUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			Feedback feedbackView = new Feedback(0,
					feedbackContent.getContent(), new Date(), user.getUser()
							.getUid(), user.getUser().getName(), user.getUser()
							.getAvatar());
			long id = feedbackDao.insert(feedbackView, placeid);
			UpdateNotifyJob job = new UpdateNotifyJob(placeid,user.getUser().getUid(),MessageEnum.FEEDBACKS.ordinal());
			job.setMessageDao(messageDao);
			asyncJobExecutor.execute(job);
			feedbackView.setId(id);
			return feedbackView;
		}
		return null;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public boolean deleteFeedback(long id) {
		// TODO Auto-generated method stub
		SecureUser user = (SecureUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return feedbackDao.deleteFeedback(id, user.getUser().getUid());
	}
}
