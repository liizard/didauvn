package admin.model;

public class ReportType {
	private int key;
	private String name;
	
	public ReportType(int key, String name) {
		this.key = key;
		this.name = name;
	}
	
	public ReportType() {
		this(0, "");
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
