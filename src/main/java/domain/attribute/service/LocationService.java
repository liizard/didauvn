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

import domain.attribute.model.District;
import domain.attribute.model.Street;
import domain.attribute.model.Ward;

public interface LocationService {
	public List<District> getDistricts(long cityId);

	public List<Ward> getWards(long districtId);

	public List<Street> getStreets(long wardId);
	
	
}
