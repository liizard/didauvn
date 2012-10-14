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

public class PlaceRq {
	public static final String NAME_EMPTY_CODE = "err201";
	public static final String NAME_EMPTY = "Place name is not specified";
	public static final String NAME_TOO_LONG_CODE = "err202";
	public static final String NAME_TOO_LONG = "Place name is too long";
	public static final String BUSINESS_INVALID_CODE = "err203";
	public static final String BUSINESS_INVALID = "Business type is invalid";
	public static final String DISTRICT_INVALID_CODE = "err204";
	public static final String DISTRICT_INVALID = "District is invalid";
	public static final String WARD_INVALID_CODE = "err205";
	public static final String WARD_INVALID = "Ward is invalid";
	public static final String STREET_INVALID_CODE = "err206";
	public static final String STREET_INVALID = "Street is invalid";
	public static final String ADDRESS_EMPTY_CODE = "err207";
	public static final String ADDRESS_EMPTY = "Address is not specified";
	public static final String ADDRESS_TOO_LONG_CODE = "err208";
	public static final String ADDRESS_TOO_LONG = "Address is too long";
	public static final String DESCRIPTION_TOO_LONG_CODE = "err209";
	public static final String DESCRIPTION_TOO_LONG = "Description is too long";
	
	private long id;
	private String name;
	private String dcrp;
	private String address;
	private String website;
	private String phone;
	private String fax;
	private String email;
	private String locationX;
	private String locationY;
	private int businessTypeId;
	private long tagId;
	private long streetId;
	private long wardId;
	private int districtId;
	private int cityId;

	public PlaceRq() {
		this(0, "", "", "", "", "", "", "", "", "", 0, 0, 0, 0, 0, 0);
	}

	public PlaceRq(long id, String name, String dcrp, String address,
			String website, String phone, String fax, String email,
			String locationX, String locationY, int businessTypeId, long tagId,
			long streetId, long wardId, int districtId, int cityId) {
		this.id = id;
		this.name = name;
		this.dcrp = dcrp;
		this.address = address;
		this.website = website;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.locationX = locationX;
		this.locationY = locationY;
		this.businessTypeId = businessTypeId;
		this.tagId = tagId;
		this.streetId = streetId;
		this.wardId = wardId;
		this.districtId = districtId;
		this.cityId = cityId;
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

	public long getStreetId() {
		return streetId;
	}

	public void setStreetId(long streetID) {
		this.streetId = streetID;
	}

	public long getWardId() {
		return wardId;
	}

	public void setWardId(long wardId) {
		this.wardId = wardId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public int getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
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
