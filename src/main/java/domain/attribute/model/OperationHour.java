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

public class OperationHour {
	private int day;
	private int openHour;
	private int closeHour;
	private String remark;
	private boolean active;
	
	public OperationHour() {
		this(1, 1, 1, "", false);
	}

	public OperationHour(int day, int openHour, int closeHour,
			String remark, boolean active) {
		super();
		this.day = day;
		this.openHour = openHour;
		this.closeHour = closeHour;
		this.remark = remark;
		this.active = active;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getOpenHour() {
		return openHour;
	}

	public void setOpenHour(int openHour) {
		this.openHour = openHour;
	}

	public int getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(int closeHour) {
		this.closeHour = closeHour;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
