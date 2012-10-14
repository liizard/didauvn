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
package domain.place.model.rq;

import domain.attribute.model.PlaceTag;

public class PlaceSearchRq {
	private int cityId;
	private int districtId;
	private int wardId;
	private int streetId;
	private String locationDisplay;
	private PlaceTag[] tags;
	private int page;

	public PlaceSearchRq() {
		cityId = 1;
		districtId = 0;
		wardId = 0;
		streetId = 0;
		locationDisplay = "";
		page = 1;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public int getStreetId() {
		return streetId;
	}

	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}

	public PlaceTag[] getTags() {
		return tags;
	}

	public void setTags(PlaceTag[] tags) {
		this.tags = tags;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getLocationDisplay() {
		return locationDisplay;
	}

	public void setLocationDisplay(String locationDisplay) {
		this.locationDisplay = locationDisplay;
	}

}
