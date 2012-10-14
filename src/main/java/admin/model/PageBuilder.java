package admin.model;

import java.util.List;

public class PageBuilder<T> {
	
	private List<T> data;
	private long pageCount;
	private long currentPage;
	
	public PageBuilder(List<T> data, long pageCount, long currentPage) {
		this.data = data;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
	}
	
	public PageBuilder() {
		this(null, 0, 0);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
}
