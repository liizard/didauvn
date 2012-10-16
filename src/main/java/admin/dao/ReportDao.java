package admin.dao;

import java.util.List;

import admin.model.*;
import admin.model.req.ReportFilterRq;
import admin.model.req.ReportRq;

public interface ReportDao {
	
	public long getPageCount();
	
	public List<ReportItem> getByPage(int page, int count);

	public List<ReportItem> getPendings();

	public List<ReportItem> getResolveds();

	public PageBuilder<ReportItem> getByFilter(ReportFilterRq filterRq);

	public void batchDelete(int[] reportItemIdArr);

	public void batchProcess(int[] reportItemIdArr);

	public void process(int reportId);

	public void delete(int reportId);
	
	public long insert(ReportRq reportRq);
	
	public boolean isDuplicate(ReportRq reportRq);
}
