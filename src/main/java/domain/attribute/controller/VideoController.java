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
package domain.attribute.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.exception.DdException;

import domain.attribute.model.Video;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;
import domain.attribute.service.VideoService;

@Controller
@RequestMapping("data/video")
public class VideoController {
	@Autowired
	private VideoService videoService;

	@ResponseBody
	@RequestMapping(value = "/get/{placeId}", method = RequestMethod.GET)
	public List<Video> getVideos(@PathVariable("placeId") long placeId,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return videoService.getVideos(placeId);
	}

	// Get more Videos
	@ResponseBody
	@RequestMapping(value = "/getmore", method = RequestMethod.POST)
	public List<Video> getMoreVideos(@RequestBody GetMoreRq getMoreRq) {
		return videoService.getMoreVideos(getMoreRq);
	}

	// Get Videos by Page
	@ResponseBody
	@RequestMapping(value = "/getbypage", method = RequestMethod.POST)
	public List<Video> getVideosPerPage(@RequestBody PageRq pageRq) {
		return videoService.getVideosByPage(pageRq);
	}

	@ResponseBody
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public Video getVideo() {
		return new Video();
	}

	@ResponseBody
	@RequestMapping(value = "/add/{placeId}", method = RequestMethod.POST)
	public Video addVideo(@PathVariable("placeId") long placeId,
			@RequestBody Video video, HttpServletResponse response)
			throws DdException {
		video.setPlace(placeId);
		long id = videoService.insertVideo(video);
		video.setId(id);
		return video;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateVideo(@RequestBody Video video,
			HttpServletResponse response) throws DdException {
		videoService.updateVideo(video);
	}

	@ResponseBody
	@RequestMapping(value = "/del/{vid}", method = RequestMethod.POST)
	public void delVideo(@PathVariable("vid") long vid) {
		videoService.deleteVideo(vid);
	}

	@ResponseBody
	@RequestMapping(value = "/getpage/{placeId}", method = RequestMethod.GET)
	public int getCount(@PathVariable("placeId") long placeId) {
		return videoService.getPageCount(placeId);
	}
}
