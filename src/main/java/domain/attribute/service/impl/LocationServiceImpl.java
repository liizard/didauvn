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
import org.springframework.stereotype.Service;

import core.context.ContextHolder;
import domain.attribute.dao.LocationDao;
import domain.attribute.model.District;
import domain.attribute.model.Street;
import domain.attribute.model.Ward;
import domain.attribute.service.LocationService;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao locationDao;

	@Override
	public List<District> getDistricts(long cityId) {
		return locationDao.getDistricts(ContextHolder.getInstance()
				.getCurrentLang().toString(), cityId);
	}

	@Override
	public List<Ward> getWards(long districtId) {
		return locationDao.getWards(ContextHolder.getInstance()
				.getCurrentLang().toString(), districtId);
	}

	@Override
	public List<Street> getStreets(long wardId) {
		return locationDao.getStreets(ContextHolder.getInstance()
				.getCurrentLang().toString(), wardId);
	}

}
