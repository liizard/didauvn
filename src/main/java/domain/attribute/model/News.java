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

import java.util.Date;

public class News {
	public static final String TITLE_EMPTY_CODE = "err210";
	public static final String TITLE_EMPTY = "Title is not specified";
	public static final String TITLE_TOO_LONG_CODE = "err211";
	public static final String TITLE_TOO_LONG = "Title is too long";
	public static final String DESCRIPTION_EMPTY_CODE = "err212";
	public static final String DESCRIPTION_EMPTY = "Description is not specified";
	public static final String DESCRIPTION_TOO_LONG_CODE = "err213";
	public static final String DESCRIPTION_TOO_LONG = "Description is too long";
	public static final String CONTENT_TOO_LONG_CODE = "err214";
	public static final String CONTENT_TOO_LONG = "Content is too long";
	public static final String IMAGE_SIZE_LIMIT_CODE = "err215";
	public static final String IMAGE_SIZE_LIMIT = "News's image size is out of limit";
	public static final String IMAGE_TYPE_NOT_ALLOW_CODE = "err216";
	public static final String IMAGE_TYPE_NOT_ALLOW = "News's image type is not allowed";

	private long id;
	private String title;
	private String desc;
	private String cont;
	private boolean promotion;
	private boolean event;
	private Date date;
	private String image;

	public News(long id, String title, String desc, String cont,
			boolean promotion, boolean event, String image) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.cont = cont;
		this.promotion = promotion;
		this.event = event;
		this.image = image;
	}

	public News(long id) {
		this(id, "", "", "", false, false, "0");
	}

	public News() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
