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
package domain.attribute.service;

import java.util.List;

import core.exception.DdException;

import domain.attribute.model.Video;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;

public interface VideoService {
	public Video getById(long id);

	public long insertVideo(Video video) throws DdException;

	public void updateVideo(Video video) throws DdException;

	public void deleteVideo(long id);

	public List<Video> getVideos(long placeId);

	public List<Video> getMoreVideos(GetMoreRq getMoreRq);
	
	public List<Video> getVideosByPage(PageRq pageRq);

	public int getPageCount(long placeId);
}
