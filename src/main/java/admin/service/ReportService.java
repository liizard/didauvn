package admin.service;

import java.util.List;

import core.exception.DdException;
import domain.attribute.model.rq.PageRq;

import admin.model.PageBuilder;
import admin.model.ReportItem;
import admin.model.ReportType;
import admin.model.req.ReportFilterRq;
import admin.model.req.ReportRq;

public interface ReportService {
	
	public long getPageCount();
	
	public List<ReportItem> getByPage(PageRq pageRq);

	public List<ReportItem> getPendings();

	public List<ReportItem> getResolveds();

	public PageBuilder<ReportItem> getByFilter(ReportFilterRq reportRq);

	public void process(int reportId);

	public void delete(int reportId);
	
	public long insert(ReportRq reportRq) throws DdException;
	
	public List<ReportType> getType();
}
