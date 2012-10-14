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
import domain.attribute.dao.BusinessTypeDao;
import domain.attribute.model.BusinessType;
import domain.attribute.service.BusinessTypeService;

@Service("businessTypeService")
public class BusinessTypeServiceImpl implements BusinessTypeService {
	@Autowired
	private BusinessTypeDao businessTypeDao;

	@Override
	public List<BusinessType> getBusinessTypes() {
		return businessTypeDao.getBusinessTypes(ContextHolder.getInstance()
				.getCurrentLang().toString());
	}
}
