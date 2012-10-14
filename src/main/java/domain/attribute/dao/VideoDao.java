/*
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

import domain.attribute.model.Video;

public interface VideoDao {
	public long insert(final Video video);

	public void update(Video video);

	public void delete(long id);

	public Video findById(long id);

	public List<Video> get(int from, int count);

	public List<Video> get();

	public int countVideos(long placeId);

	public List<Video> getVideos(long placeId);
	
	public List<Video> getMoreVideos(long placeId, long lastId, int count);
	
	public List<Video> getVideosByPage(long placeId, int page, int count);
}
