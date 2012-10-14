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
package domain.attribute.model;

public class Image {
	public static final String CAPTION_EMPTY_CODE = "err241";
	public static final String CAPTION_EMPTY = "Image caption is not specified";
	public static final String IMAGE_SIZE_LIMIT_CODE = "err242";
	public static final String IMAGE_SIZE_LIMIT = "Image size is out of limit";
	public static final String IMAGE_TYPE_NOT_ALLOW_CODE = "err243";
	public static final String IMAGE_TYPE_NOT_ALLOW = "Image type is not allowed";
	public static final String CAPTION_TOO_LONG_CODE = "err244";
	public static final String CAPTION_TOO_LONG = "Image caption is too long";

	private long id;
	private String caption;
	private long placeId;

	public Image() {
	}

	public Image(long id, String caption, long placeId) {
		super();
		this.id = id;
		this.caption = caption;
		this.placeId = placeId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

}
