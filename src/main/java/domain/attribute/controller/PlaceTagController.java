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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.PlaceTag;
import domain.attribute.model.TagUpdate;
import domain.attribute.model.rq.TagRq;
import domain.attribute.service.PlaceTagService;

@Controller
@RequestMapping("data/attr/placetag")
public class PlaceTagController {

	@Autowired
	PlaceTagService placeTagService;
	@Autowired
	MappingJacksonHttpMessageConverter jsonConverter;

//------------------------------- PLACE TAGS --------------------------------------------------
	
	// ------------ Get Tags of a Place ------------------------
	@RequestMapping(value = "/{placeid}", method = RequestMethod.GET)
	@ResponseBody
	public List<PlaceTag> getPlaceTags(@PathVariable("placeid") long placeId) {
		return placeTagService.getPlaceTags(placeId);
	}

	// ------------ Get Main Tags of a Place ------------------------
	@RequestMapping(value = "maintag/{placeid}", method = RequestMethod.GET)
	@ResponseBody
	public PlaceTag getMainTags(@PathVariable("placeid") long placeId) {
		return placeTagService.getMainTag(placeId);
	}

	// ------------ Get Main Tags of a Place ------------------------
	@RequestMapping(value = "maintag/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void updateMainTags(@PathVariable("placeid") long placeId,
			@RequestBody PlaceTag tag) {
		placeTagService.updateMainTag(placeId, tag.getId());
	}

	// ------------- Insert new Tags --------------------------
	@RequestMapping(value = "/new/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void insertPlaceTags(@PathVariable("placeid") long placeId,
			@RequestBody PlaceTag[] tags) {
		List<PlaceTag> tagsList = Arrays.asList(tags);
		placeTagService.insertPlaceTags(placeId, tagsList);
	}

	// ------------- Delete Tags --------------------------
	@RequestMapping(value = "/delete/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void deletePlaceTags(@PathVariable("placeid") long placeId,
			@RequestBody PlaceTag[] tags) {
		List<PlaceTag> tagsList = Arrays.asList(tags);
		placeTagService.deletePlaceTags(placeId, tagsList);
	}

	// -------------- Get Null Tag Update --------------------------
	@RequestMapping(value = "/emptyupdatetag", method = RequestMethod.POST)
	@ResponseBody
	public TagUpdate updateEmptyTag() {
		return new TagUpdate();
	}

	// ------------- Update Tag----------------
	@RequestMapping(value = "/updatetag/{placeId}", method = RequestMethod.POST)
	@ResponseBody
	public void updatePlaceTag(@PathVariable("placeId") long placeId,
			@RequestBody TagUpdate tagUpdate) {
		placeTagService.insertPlaceTags(placeId, tagUpdate.getAdd());
		placeTagService.deletePlaceTags(placeId, tagUpdate.getRemove());
		placeTagService.updateMainTag(placeId, tagUpdate.getMainTag().getId());
	}
	

//------------------------------- TAGS --------------------------------------------------

	// ------------ Get All Tags ------------------------
	@RequestMapping(value = "/gets", method = RequestMethod.POST)
	@ResponseBody
	public List<PlaceTag> getAllTags(@RequestBody TagRq tagRq) {
		return placeTagService.getTagsByRequest(tagRq);
	}
	
	// return empty Tag Request
	@RequestMapping(value = "/gettagrq", method = RequestMethod.POST)
	@ResponseBody
	public TagRq getEmptyTagRq() {
		return new TagRq();
	}
}
