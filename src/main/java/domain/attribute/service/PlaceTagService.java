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

import domain.attribute.model.PlaceTag;
import domain.attribute.model.rq.TagRq;

public interface PlaceTagService {
	
	// Functions for Place Tags table

	public List<PlaceTag> getPlaceTags(long placeId);
	
	public PlaceTag getMainTag(long placeId);
	
	public void updateMainTag(long placeId, long tagId);
	
	public void insertPlaceTags(long placeId, List<PlaceTag> tags);
	
	public void deletePlaceTags(long placeId, List<PlaceTag> tags);
	
	// Function for Tags table
	
	public List<PlaceTag> getTagsByRequest(TagRq tagRq);
}
