package admin.model.req;


public class ReportFilterRq {
	private boolean processedItemSelected;
	private boolean unProcessedItemSelected;
	private int reportTypeValue;
	private String from;
	private String to;
	private int page;

	public ReportFilterRq(boolean processedItemSelected,
			boolean unProcessedItemSelected, int reportTypeValue,
			String from, String to, int page) {
		this.processedItemSelected = processedItemSelected;
		this.unProcessedItemSelected = unProcessedItemSelected;
		this.reportTypeValue = reportTypeValue;
		this.from = from;
		this.to = to;
		this.page = page;
	}

	public ReportFilterRq() {
		this(false, true, 0, new String(), new String(), 0);
	}

	public boolean isProcessedItemSelected() {
		return processedItemSelected;
	}

	public void setProcessedItemSelected(boolean processedItemSelected) {
		this.processedItemSelected = processedItemSelected;
	}

	public boolean isUnProcessedItemSelected() {
		return unProcessedItemSelected;
	}

	public void setUnProcessedItemSelected(boolean unProcessedItemSelected) {
		this.unProcessedItemSelected = unProcessedItemSelected;
	}

	public int getReportTypeValue() {
		return reportTypeValue;
	}

	public void setReportTypeValue(int reportTypeValue) {
		this.reportTypeValue = reportTypeValue;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
