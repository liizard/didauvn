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
package domain.place.service;

import java.util.List;

import domain.attribute.model.NewsView;
import domain.attribute.model.OfferWeek;

public interface GoAroundService {

	public List<NewsView> getNewsByCategory(int category, int from);
	
	public List<OfferWeek> getOfferByCategory(int category, int from);
	
	
}
