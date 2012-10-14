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
import domain.attribute.model.Image;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;
import domain.attribute.service.ImageService;

@Controller
@RequestMapping("data/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	private ObjectMapper mapper = new ObjectMapper();

	@ResponseBody
	@RequestMapping(value = "/get/{placeId}", method = RequestMethod.POST)
	public List<Image> getImg(@PathVariable("placeId") long placeId,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return imageService.getImages(placeId);
	}
	
	// Get More Image
	@ResponseBody
	@RequestMapping(value = "/getmore", method = RequestMethod.POST)
	public List<Image> getMoreImage(@RequestBody GetMoreRq getMoreRq) {
		return imageService.getMoreImages(getMoreRq);
	}

	// Get Image by Page
	@ResponseBody
	@RequestMapping(value = "/getbypage", method = RequestMethod.POST)
	public List<Image> getImageByPage(@RequestBody PageRq pageRq) {
		return imageService.getImagesByPage(pageRq);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getpagecount/{placeId}", method = RequestMethod.POST)
	public long getImagesCount(@PathVariable("placeId") long placeId) {
		return imageService.getPageNumber(placeId);
	}

	@ResponseBody
	@RequestMapping(value = "/get/new", method = RequestMethod.GET)
	public Image getImgView() {
		return new Image();
	}

	@ResponseBody
	@RequestMapping(value = "/update/{imageid}", method = RequestMethod.POST)
	public void updateImg(@RequestBody Image imageView) throws DdException {
		imageService.updateImage(imageView);
	}

	@ResponseBody
	@RequestMapping(value = "/del/{imageid}", method = RequestMethod.POST)
	public void delImg(@PathVariable("imageid") long imageId) {
		imageService.deleteImage(imageId);
	}

	@ResponseBody
	@RequestMapping(value = "/setAva/{placeId}/{imageId}", method = RequestMethod.POST)
	public void setAvatar(@PathVariable("placeId") long placeId,
			@PathVariable("imageId") long imageId) {
		imageService.setPlaceAvatar(imageId, placeId);
	}

	@RequestMapping(value = "/upimg/{placeId}", method = RequestMethod.POST)
	public void upImg(@PathVariable("placeId") long placeId, ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("file");
		Image imageView = imageService.insertImage(imgFile,
				request.getParameter("caption"), placeId);
		List<Image> images = new ArrayList<Image>();
		response.setContentType("text/plain");
		images.add(imageView);
		response.getWriter().write(mapper.writeValueAsString(images));
	}

}
