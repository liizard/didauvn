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
package admin.model.req;

public class ReportRq {

	public static final String DESCRIPTION_EMPTY_CODE = "err261";
	public static final String DESCRIPTION_EMPTY = "Description is not specified";
	public static final String DESCRIPTION_TOO_LONG_CODE = "err262";
	public static final String DESCRIPTION_TOO_LONG = "Description is too long";
	
	private long id;
	public String reportType;
	private long itemId;
	private long user;
	private String dcrp;
	private boolean processed;

	public ReportRq(long id, String reportType, long itemId, long user,
			String dcrp, boolean processed) {
		this.id = id;
		this.reportType = reportType;
		this.itemId = itemId;
		this.user = user;
		this.dcrp = dcrp;
		this.processed = processed;
	}
	
	public ReportRq() {
		this(0, "", 0, 0, "", false);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public String getDcrp() {
		return dcrp;
	}

	public void setDcrp(String dcrp) {
		this.dcrp = dcrp;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
