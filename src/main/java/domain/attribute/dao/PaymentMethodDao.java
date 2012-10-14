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

import domain.attribute.model.PaymentMethod;

public interface PaymentMethodDao {
	
	public void updatePaymentMethod(PaymentMethod paymentMethod);

	public PaymentMethod getPaymentMethods(long placeId);
}
