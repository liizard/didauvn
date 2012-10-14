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

import domain.attribute.model.PlaceTag;

public interface PlaceTagDao {
	
	// Functions for PlaceTag table
	
	public List<PlaceTag> getPlaceTags(long placeId);
	
	public PlaceTag getMainTag(long placeId);
	
	public void updateMainTag(long placeId, long tagId);
	
	public void insertPlaceTags(long placeId, List<PlaceTag> tags);
	
	public void deletePlaceTags(long placeId, List <PlaceTag> tags);
	
	// Functions for Tag table

	public List<PlaceTag> getTagsByRequest(String request);
	
	public void updateRank(PlaceTag[] tags);
}
