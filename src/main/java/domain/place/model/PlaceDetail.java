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
package domain.place.model;

public class PlaceDetail {
	private long id;
	private long avatar;
	private String name;
	private String dcrp;
	private String address;
	private String website;
	private String phone;
	private String fax;
	private String email;
	private String tagName;
	private String businessTypeName;
	private String streetName;
	private String wardName;
	private String districtName;
	private String cityName;
	private String locationX;
	private String locationY;

	
	public PlaceDetail(long id, long avatar, String name, String dcrp,
			String address, String website, String phone, String fax,
			String email, String tagName, String businessTypeName,
			String streetName, String wardName, String districtName,
			String cityName, String locationX, String locationY) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.dcrp = dcrp;
		this.address = address;
		this.website = website;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.tagName = tagName;
		this.businessTypeName = businessTypeName;
		this.streetName = streetName;
		this.wardName = wardName;
		this.districtName = districtName;
		this.cityName = cityName;
		this.locationX = locationX;
		this.locationY = locationY;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDcrp() {
		return dcrp;
	}

	public void setDcrp(String dcrp) {
		this.dcrp = dcrp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public long getAvatar() {
		return avatar;
	}

	public void setAvatar(long avatar) {
		this.avatar = avatar;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	
}
