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

import domain.attribute.model.OperationHour;

public interface OperationHourService {
	public void insert(long placeId);

	public void update(long placeId, List<OperationHour> operationHours);

	public List<OperationHour> getOperationHours(long placeId);

}
