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

import core.job.executor.AsyncJobExecutor;
import domain.attribute.dao.MessageDao;
import domain.attribute.dao.PaymentMethodDao;
import domain.attribute.model.PaymentMethod;
import domain.attribute.service.PaymentMethodService;

@Service("paymentMethodService")
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	@Override
	public PaymentMethod getPaymentMethod(long placeId) {
		return paymentMethodDao.getPaymentMethods(placeId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#paymentMethod.getPlaceId(),'hasPlaceOwnerRight') or hasPermission(#paymentMethod.getPlaceId(),'hasPlaceRight')))")
	public void updatePaymentMethod(PaymentMethod paymentMethod) {
		paymentMethodDao.updatePaymentMethod(paymentMethod);

		// Execute Async Job
//		UpdateNotifyJob job = new UpdateNotifyJob(paymentMethod.getPlaceId(),
//				ContextHolder.getInstance().getCurrentUserId(),
//				MessageEnum.PAYMENT_METHOD.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
	}
}
