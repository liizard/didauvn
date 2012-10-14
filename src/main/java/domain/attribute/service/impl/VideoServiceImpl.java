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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import core.exception.DdException;
import core.job.executor.AsyncJobExecutor;
import domain.attribute.dao.MessageDao;
import domain.attribute.dao.VideoDao;
import domain.attribute.model.Video;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;
import domain.attribute.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoDao videoDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	@Override
	public Video getById(long id) {
		return videoDao.findById(id);
	}

	@Override
	public List<Video> getVideos(long placeId) {
		return videoDao.getVideos(placeId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#video.getPlace(),'hasPlaceOwnerRight') or hasPermission(#video.getPlace(),'hasPlaceRight')))")
	public long insertVideo(Video video) throws DdException {
		if (video.getHref().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Video.HREF_EMPTY_CODE, Video.HREF_EMPTY);
		}
		captionValidate(video);

		Pattern rex = Pattern.compile("(?<=videos\\/|v=|.be/)([\\w-]+)");
		Matcher m = rex.matcher(video.getHref());
		if (m.find())
			video.setHref(m.group());
		else
			return 0;

		// Execute Async Job
//		UpdateNotifyJob job = new UpdateNotifyJob(video.getPlace(),
//				ContextHolder.getInstance().getCurrentUserId(),
//				MessageEnum.VIDEO.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
		return videoDao.insert(video);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#video.getId(),'hasVideoOwnerRight') or hasPermission(#video.getId(),'hasVideoRight')))")
	public void updateVideo(Video video) throws DdException {
		captionValidate(video);
		videoDao.update(video);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#id,'hasVideoOwnerRight') or hasPermission(#id,'hasVideoRight')))")
	public void deleteVideo(long id) {
		videoDao.delete(id);
	}

	// Get More Videos
	@Override
	public List<Video> getMoreVideos(GetMoreRq getMoreRq) {
		return videoDao.getMoreVideos(getMoreRq.getPlaceId(),
				getMoreRq.getLastId(), WebConstant.VIDEO_PER_PAGE + 1);
	}

	// Get Video By Page
	@Override
	public List<Video> getVideosByPage(PageRq pageRq) {
		return videoDao.getVideosByPage(pageRq.getPlaceId(), pageRq.getPage()
				* WebConstant.VIDEO_PER_PAGE, WebConstant.VIDEO_PER_PAGE + 1);
	}

	// Get number of Video page
	@Override
	public int getPageCount(long placeId) {
		int pageNum = 0;
		pageNum = (int) Math.ceil((double) videoDao.countVideos(placeId)
				/ WebConstant.VIDEO_PER_PAGE);
		return pageNum;
	}

	private void captionValidate(Video video) throws DdException {
		if (video.getCaption().length() > 200) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Video.CAPTION_TOO_LONG_CODE, Video.CAPTION_TOO_LONG);
		}
	}
}
