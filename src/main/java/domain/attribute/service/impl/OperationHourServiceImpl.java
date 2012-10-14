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
import domain.attribute.dao.OperationHourDao;
import domain.attribute.model.OperationHour;
import domain.attribute.service.OperationHourService;

@Service("operationHourService")
public class OperationHourServiceImpl implements OperationHourService {

	@Autowired
	private OperationHourDao operationHourDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public void insert(long placeId) {
		operationHourDao.insert(placeId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public void update(long placeId, List<OperationHour> operationHours) {
		operationHourDao.update(placeId, operationHours);

		// asynchronous job update
//		UpdateNotifyJob job = new UpdateNotifyJob(placeId, ContextHolder
//				.getInstance().getCurrentUserId(),
//				MessageEnum.OPERATION_HOUR.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
	}

	@Override
	public List<OperationHour> getOperationHours(long placeId) {
		List<OperationHour> hours = null;
		hours = operationHourDao.getOperationHours(placeId);
		return hours;
	}

}
