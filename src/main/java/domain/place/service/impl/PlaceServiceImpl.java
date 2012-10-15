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
package domain.place.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import core.context.ContextHolder;
import core.exception.DdException;
import core.job.executor.AsyncJobExecutor;
import core.util.SecurityUtil;
import domain.attribute.dao.MessageDao;
import domain.place.dao.PlaceDao;
import domain.place.model.PlaceDetail;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceRq;
import domain.place.model.rq.PlaceSearchRq;
import domain.place.service.PlaceService;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	private void validate(PlaceRq place) throws DdException {
		if (place.getName().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.NAME_EMPTY_CODE, PlaceRq.NAME_EMPTY);
		}
		if (place.getName().length() > 100) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.NAME_TOO_LONG_CODE, PlaceRq.NAME_TOO_LONG);
		}
		if (place.getBusinessTypeId() < 1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.BUSINESS_INVALID_CODE, PlaceRq.BUSINESS_INVALID);
		}
		if (place.getDistrictId() < 1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.DISTRICT_INVALID_CODE, PlaceRq.DISTRICT_INVALID);
		}
		if (place.getWardId() < 1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.WARD_INVALID_CODE, PlaceRq.WARD_INVALID);
		}
		if (place.getStreetId() < 1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.STREET_INVALID_CODE, PlaceRq.STREET_INVALID);
		}
		if (place.getAddress().length() <= 0) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.ADDRESS_EMPTY_CODE, PlaceRq.ADDRESS_EMPTY);
		}
		if (place.getAddress().length() > 200) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.ADDRESS_TOO_LONG_CODE, PlaceRq.ADDRESS_TOO_LONG);
		}
		if (place.getDcrp().length() > 10000) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					PlaceRq.DESCRIPTION_TOO_LONG_CODE,
					PlaceRq.DESCRIPTION_TOO_LONG);
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public long insertPlace(PlaceRq place) throws DdException {

		place.setDcrp(SecurityUtil.secureTextToEditor(place.getDcrp()));

		// *Validation start
		validate(place);
		// *Validation end

		if (!NumberUtils.isNumber(place.getLocationX())) {
			place.setLocationX(String.valueOf(0));
		}

		if (!NumberUtils.isNumber(place.getLocationY())) {
			place.setLocationY(String.valueOf(0));
		}
		long placeId = placeDao.insertPlace(place, ContextHolder.getInstance()
				.getCurrentUserId());
		// Set owner for new place
		ContextHolder.getInstance().getUserPlaceOwner().add(placeId);
		return placeId;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#place.getId(),'hasPlaceOwnerRight') or hasPermission(#place.getId(),'hasPlaceRight')))")
	public void updatePlace(PlaceRq place) throws DdException {
		validate(place);
		if (!NumberUtils.isNumber(place.getLocationX())) {
			place.setLocationX(String.valueOf(0));
		}

		if (!NumberUtils.isNumber(place.getLocationY())) {
			place.setLocationY(String.valueOf(0));
		}
		place.setDcrp(SecurityUtil.secureTextToEditor(place.getDcrp()));

		placeDao.updatePlace(place);
		
//		UpdateNotifyJob job = new UpdateNotifyJob(place.getId(), ContextHolder
//				.getInstance().getCurrentUserId(),
//				MessageEnum.GENERAL_INFORMATION.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
	}

	@Override
	public PlaceRq getPlace(long id) {
		return placeDao.getPlace(id);
	}

	@Override
	public PlaceDetail getPlaceDetail(long id) {
		PlaceDetail placeDetail = placeDao.getPlaceDetail(id, ContextHolder
				.getInstance().getCurrentLang().toString());
		List<Long> ids = ContextHolder.getInstance().getUserPlaceVisited();
		if (ids == null) {
			placeDao.updateViewCount(id);
			ids = new ArrayList<Long>();
			ids.add(id);
			ContextHolder.getInstance().setUserPlaceVisited(ids);
		} else if (!ids.contains(id)) {
			placeDao.updateViewCount(id);
			ids.add(id);
		}
		//placeDetail.setWowCount(placeDao.getWowCount(id));
		return placeDetail;
	}

	@Override
	public List<PlaceGeneral> searchPlace(PlaceSearchRq searchRequest) {
		// Get list tag id for query
		String tagIds = "";
		for (int i = 0; i < searchRequest.getTags().length; i++) {
			tagIds += searchRequest.getTags()[i].getId() + ",";
		}
		// Remove last comma
		if (tagIds.length() > 0) {
			tagIds = tagIds.substring(0, tagIds.length() - 1);
		}

		String locationQuery = "";
		if (searchRequest.getStreetId() > 0) {
			locationQuery = "StreetId=" + searchRequest.getStreetId();
		} else if (searchRequest.getWardId() > 0) {
			locationQuery = "WardId=" + searchRequest.getWardId();
		} else if (searchRequest.getDistrictId() > 0) {
			locationQuery = "DistrictId=" + searchRequest.getDistrictId();
		} else {
			locationQuery = "CityId=" + searchRequest.getCityId();
		}

		if (searchRequest.getPage() < 1)
			searchRequest.setPage(1);

		return placeDao.searchPlace(ContextHolder.getInstance()
				.getCurrentLang().toString(), tagIds, locationQuery,
				(searchRequest.getPage() - 1)
						* WebConstant.SEARCH_RESULT_PER_PAGE,
				WebConstant.SEARCH_RESULT_PER_PAGE + 1);
	}
}
