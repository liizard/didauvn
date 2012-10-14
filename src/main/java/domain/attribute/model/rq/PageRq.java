package domain.attribute.model.rq;

public class PageRq {
	private long placeId;
	private int page;
	
	public PageRq(long placeId, int page) {
		this.placeId = placeId;
		this.page = page;
	}
	
	public PageRq() {
		this(0, 1);
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
