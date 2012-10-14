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

public class Password {
	public static final String OLDPASS_EMPTY_CODE = "err251";
	public static final String OLDPASS_EMPTY = "Old password is not specified";
	public static final String NEWPASS_EMPTY_CODE = "err252";
	public static final String NEWPASS_EMPTY = "New password is not specified";
	public static final String OLDPASS_WRONG_CODE = "err253";
	public static final String OLDPASS_WRONG = "Old password is wrong";
	
	private String oldpass;
	private String newpass;

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
}
