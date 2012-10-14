package domain.attribute.model.rq;

public class GetMoreRq {
	private long placeId;
	private long lastId;
	private boolean viewMore;

	public GetMoreRq() {
		this(0, 0, false);
	}

	public GetMoreRq(long placeId, long lastId, boolean viewMore) {
		this.placeId = placeId;
		this.lastId = lastId;
		this.viewMore = viewMore;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastId) {
		this.lastId = lastId;
	}

	public boolean isViewMore() {
		return viewMore;
	}

	public void setViewMore(boolean viewMore) {
		this.viewMore = viewMore;
	}

}
