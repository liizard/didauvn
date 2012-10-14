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

public class Video {
	public static final String HREF_EMPTY_CODE = "err231";
	public static final String HREF_EMPTY = "Video link is not specified";
	
	public static final String CAPTION_EMPTY_CODE = "err232";
	public static final String CAPTION_EMPTY = "Video caption is not specified";
	public static final String CAPTION_TOO_LONG_CODE = "err233";
	public static final String CAPTION_TOO_LONG = "Video caption is too long";
	
	private long id;
	private String caption;
	private String href;
	private long place;

	public Video(long id, String caption, String href, long place) {
		super();
		this.id = id;
		this.caption = caption;
		this.href = href;
		this.place = place;
	}

	public Video() {
		caption = "";
		href = "";
		place = 0;
		id = 0;
		
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public long getPlace() {
		return place;
	}

	public void setPlace(long place) {
		this.place = place;
	}
}
