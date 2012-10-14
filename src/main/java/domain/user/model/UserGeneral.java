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

public class UserGeneral {
	private long uid;
	private String name;
	private String gender;
	private String birthday;
	private String createdate;
	private String profileFB;
	private String profileTwitter;
	
	public UserGeneral(){
		this.uid = 0;
	}
	public UserGeneral(long uid,String name, String gender, String birthday,
			String createdate, String profileFB,String profileTwitter) {
		this.uid = uid;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.createdate = createdate;
		this.profileFB = profileFB;
		this.profileTwitter = profileTwitter;
	}
	
	public String getProfileFB() {
		return profileFB;
	}
	public void setProfileFB(String profileFB) {
		this.profileFB = profileFB;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getProfileTwitter() {
		return profileTwitter;
	}
	public void setProfileTwitter(String profileTwitter) {
		this.profileTwitter = profileTwitter;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}
