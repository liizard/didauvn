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
package domain.user.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	public static final String EMAIL_EMPTY_CODE = "err220";
	public static final String EMAIL_EMPTY = "Email is not specified";
	public static final String EMAIL_INVALID_CODE = "err221";
	public static final String EMAIL_INVALID = "Email is already used or invalid";
	public static final String NAME_EMPTY_CODE = "err222";
	public static final String NAME_EMPTY = "Name is not specified";
	public static final String NAME_TOO_LONG_CODE = "err223";
	public static final String NAME_TOO_LONG = "Name is too long";
	public static final String BIRTHDAY_EMPTY_CODE = "err224";
	public static final String BIRTHDAY_EMPTY = "Birthday is not specified";
	public static final String PASSWORD_EMPTY_CODE = "err225";
	public static final String PASSWORD_EMPTY = "Password is not specified";
	public static final String PASSWORD_TOO_SHORT_CODE = "err226";
	public static final String PASSWORD_TOO_SHORT = "Password length must be larger than 5 characters";
	public static final String AVATAR_SIZE_LIMIT_CODE = "err227";
	public static final String AVATAR_SIZE_LIMIT = "Avatar size is out of limit";
	public static final String AVATAR_TYPE_NOT_ALLOW_CODE = "err228";
	public static final String AVATAR_TYPE_NOT_ALLOW = "Avatar type is not allowed";
	public static final String BIRTHDAY_INVALID_CODE = "error2200";
	public static final String BIRTHDAY_INVALID = "Birthday is invalid";

	private static final long serialVersionUID = -6607655450844340981L;
	private long uid;
	private String email;
	private String password;
	private String name;
	private String birthday;
	private int status;
	private String gender;
	private Date createDate;
	private String avatar;
	private boolean isFacebookUser;

	public User() {
		this.uid = 0;
		this.email = "";
		this.password = "";
		this.name = "";
		this.birthday = "";
		this.isFacebookUser = false;
	}

	public User(long uid, String email, String password, String name,
			String birthday, int status, String gender, Date createDate,
			String picture, String avatar, boolean isFacebookUser) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.status = status;
		this.gender = gender;
		this.createDate = createDate;
		this.avatar = avatar;
		this.isFacebookUser = isFacebookUser;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isFacebookUser() {
		return isFacebookUser;
	}

	public void setFacebookUser(boolean isFacebookUser) {
		this.isFacebookUser = isFacebookUser;
	}

}
