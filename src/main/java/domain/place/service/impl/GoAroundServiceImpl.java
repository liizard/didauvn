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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import domain.attribute.dao.NewsDao;
import domain.attribute.dao.OfferDao;
import domain.attribute.model.NewsView;
import domain.attribute.model.OfferWeek;
import domain.place.service.GoAroundService;

@Service("goAroundService")
public class GoAroundServiceImpl implements GoAroundService {
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private OfferDao offerDao;

	@Override
	public List<NewsView> getNewsByCategory(int categoryId, int from) {
		//return newsDao.getByCategory(categoryId, from * WebConstant.NEWS_PER_PAGE, WebConstant.NEWS_PER_PAGE + 1);
		return newsDao.getByCategory(categoryId, from, WebConstant.NEWS_PER_PAGE + 1);
	}

	@Override
	public List<OfferWeek> getOfferByCategory(int cateogryId, int from) {
		return offerDao.getOffer(cateogryId, from * WebConstant.OFFER_PER_PAGE, WebConstant.OFFER_PER_PAGE + 1);
	}

}
