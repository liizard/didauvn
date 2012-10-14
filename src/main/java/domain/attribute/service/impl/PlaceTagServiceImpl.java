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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.job.executor.AsyncJobExecutor;
import domain.attribute.dao.MessageDao;
import domain.attribute.dao.PlaceTagDao;
import domain.attribute.model.PlaceTag;
import domain.attribute.model.rq.TagRq;
import domain.attribute.service.PlaceTagService;

@Service("placeTagService")
public class PlaceTagServiceImpl implements PlaceTagService {

	@Autowired
	private PlaceTagDao placeTagDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	@Override
	public List<PlaceTag> getPlaceTags(long placeId) {
		return placeTagDao.getPlaceTags(placeId);
	}

	@Override
	public PlaceTag getMainTag(long placeId) {
		return placeTagDao.getMainTag(placeId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and hasPermission(#placeId,'hasPlaceOwnerRight'))")
	public void updateMainTag(long placeId, long tagId) {
		placeTagDao.updateMainTag(placeId, tagId);

		// asynchronous job update
//		UpdateNotifyJob job = new UpdateNotifyJob(placeId, ContextHolder
//				.getInstance().getCurrentUserId(),
//				MessageEnum.MAIN_TAG.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public void insertPlaceTags(long placeId, List<PlaceTag> tags) {
		if (tags.size() == 0)
			return;
		placeTagDao.insertPlaceTags(placeId, tags);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public void deletePlaceTags(long placeId, List<PlaceTag> tags) {
		if (tags.size() == 0)
			return;
		placeTagDao.deletePlaceTags(placeId, tags);
	}

	@Override
	public List<PlaceTag> getTagsByRequest(TagRq tagRq) {
		return placeTagDao.getTagsByRequest(tagRq.getName());
	}
}
