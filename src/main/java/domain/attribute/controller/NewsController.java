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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import core.exception.DdException;

import domain.attribute.model.News;
import domain.attribute.service.NewsService;

@Controller
@RequestMapping("data/news")
public class NewsController {
	@Autowired
	NewsService newsService;
	
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/new/{newsId}", method = RequestMethod.POST)
	@ResponseBody
	public News getBlankNews(@PathVariable("newsId") long newsId) {
		return new News(newsId);
	}

	@RequestMapping(value = "/getbyplace/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public News getNewsByPlace(@PathVariable("placeid") long placeId) {
		return newsService.getByPlace(placeId);
	}

	@RequestMapping(value = "/add/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public News addNews(@RequestBody News news,
			@PathVariable("placeid") long placeId) throws DdException {
			newsService.insert(news, placeId);
			return news;
	}

	@RequestMapping(value = "/update/{newsId}", method = RequestMethod.POST)
	@ResponseBody
	public News updateNews(@RequestBody News news,
			@PathVariable("newsId") long newsId) throws DdException {
		newsService.update(news, newsId);
		return news;
	}
	
	@RequestMapping(value = "/upimg/{newsId}", method = RequestMethod.POST)
	public String upImg(@PathVariable("newsId") long newsId,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgFile = multipartRequest.getFile("file");
			long imageId;
			imageId = newsService.insertImage(imgFile, newsId);
			List<Long> images = new ArrayList<Long>();
			response.setContentType("text/plain");
			images.add(imageId);
			response.getWriter().write(mapper.writeValueAsString(images));
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
