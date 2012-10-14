package admin.model;

import java.util.Date;
 
public class ReportItem {
	// *** These Link need to be adjusted ***
	public static final String PLACE_LINK_TO = "/place/";
	public static final String IMAGE_LINK_TO = "/data/img/gallery/";
	public static final String VIDEO_LINK_TO = "/data/img/video/";
	public static final String OF_PLACE =  "/data/video/";

	private long id;
	private long itemId;
	private long user;
	public String reportType;
	private String dcrp;
	private Date createDate;
	private boolean processed;
	public String ofPlace;
	public String linkTo;
	
	public ReportItem() {

	}

	public ReportItem(long id, long itemId, long user, String reportType, String dcrp,
			Date createDate, boolean processed) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.user = user;
		this.reportType = reportType;
		this.dcrp = dcrp;
		this.createDate = createDate;
		this.processed = processed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getDcrp() {
		return dcrp;
	}

	public void setDcrp(String dcrp) {
		this.dcrp = dcrp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean isProcessed) {
		this.processed = isProcessed;
	}

	public String getOfPlace() {
		return ofPlace;
	}

	public void setOfPlace(String ofPlace) {
		this.ofPlace = ofPlace;
	}

	public String getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}
	
}
