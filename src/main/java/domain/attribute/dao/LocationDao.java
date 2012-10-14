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

import domain.attribute.model.District;
import domain.attribute.model.Street;
import domain.attribute.model.Ward;
import domain.place.model.LocationSearch;

public interface LocationDao {
	public List<District> getDistricts(String locale, long cityId);

	public List<Ward> getWards(String locale, long districtId);

	public List<Street> getStreets(String locale, long wardId);

	public List<LocationSearch> searchLocation(String locale,
			String location);	
}
