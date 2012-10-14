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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import core.constant.SystemConstant;
import core.context.ContextHolder;
import core.exception.DdException;
import core.job.UpdateNotifyJob;
import core.job.executor.AsyncJobExecutor;
import core.util.ImageUtil;
import domain.attribute.dao.MessageDao;
import domain.attribute.dao.NewsDao;
import domain.attribute.model.MessageEnum;
import domain.attribute.model.News;
import domain.attribute.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDao newsDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	private String[] allowType = { "image/gif", "image/jpeg", "image/pjpeg",
			"image/png" };

	private ImageUtil imageUtil = new ImageUtil(2 * 1024 * 1024,
			".jpg.png.gif", allowType, SystemConstant.PATH_NEWS);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public long insert(News news, long placeId) throws DdException {
		validateNews(news);
		long id = newsDao.insert(news, placeId);
		news.setId(id);

		// Execute Async Job
		UpdateNotifyJob job = new UpdateNotifyJob(placeId, ContextHolder
				.getInstance().getCurrentUserId(),
				MessageEnum.INSERT_NEWS.ordinal());
		job.setMessageDao(messageDao);
		asyncJobExecutor.execute(job);
		
		return id;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#newsId,'hasNewsOwnerRight') or hasPermission(#newsId,'hasNewsRight')))")
	public void update(News news, long newsId) throws DdException {
		validateNews(news);
		newsDao.update(news, newsId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#id,'hasNewsOwnerRight') or hasPermission(#id,'hasNewsRight')))")
	public void delete(long id) {
		newsDao.delete(id);
	}

	@Override
	public News getByPlace(long placeId) {
		return newsDao.getByPlace(placeId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#newsId,'hasNewsOwnerRight') or hasPermission(#newsId,'hasNewsRight')))")
	public long insertImage(MultipartFile multipartFile, long newsId)
			throws Exception {
		try {
			// Get image and delete if not default image
			long curImageId = getImage(newsId);
			if (curImageId > 0) {
				imageUtil.deleteImg(curImageId + ".jpg");
			}
			long imageId = System.currentTimeMillis();
			imageUtil.resizeByWidth(multipartFile, imageId + ".jpg", 230, 0);
			newsDao.updateImage(newsId, imageId);
			return imageId;
		} catch (DdException e) {

			if (e.getCode().equalsIgnoreCase(ImageUtil.FILE_SIZE_LIMIT_CODE)) {
				throw new DdException(DdException.VALIDATION_EXCEPTION,
						News.IMAGE_SIZE_LIMIT_CODE, News.IMAGE_SIZE_LIMIT, e);

			} else if (e.getCode().equalsIgnoreCase(
					ImageUtil.FILE_TYPE_NOT_ALLOW_CODE)) {
				throw new DdException(DdException.VALIDATION_EXCEPTION,
						News.IMAGE_TYPE_NOT_ALLOW_CODE,
						News.IMAGE_TYPE_NOT_ALLOW, e);
			} else {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public long getImage(long newsId) {
		return newsDao.getImage(newsId);
	}

	private void validateNews(News news) throws DdException {
		if (news.getTitle().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					News.TITLE_EMPTY_CODE, News.TITLE_EMPTY);
		}
		if (news.getTitle().length() > 100) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					News.TITLE_TOO_LONG_CODE, News.TITLE_TOO_LONG);
		}
		if (news.getDesc().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					News.DESCRIPTION_EMPTY_CODE, News.DESCRIPTION_EMPTY);
		}
		if (news.getDesc().length() > 500) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					News.DESCRIPTION_TOO_LONG_CODE, News.DESCRIPTION_TOO_LONG);
		}
		if (news.getCont().length() > 2000) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					News.CONTENT_TOO_LONG_CODE, News.CONTENT_TOO_LONG);
		}
	}
}
