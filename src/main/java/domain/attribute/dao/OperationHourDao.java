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

import domain.attribute.model.OperationHour;


public interface OperationHourDao {
	
	public List<OperationHour> getOperationHours(long placeId);
	
	public void insert(long placeId);
	
	public void update(long placeId, List<OperationHour> operationHours);
	
}
