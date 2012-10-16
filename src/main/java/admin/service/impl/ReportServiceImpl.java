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
package admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import admin.dao.ReportDao;
import admin.model.PageBuilder;
import admin.model.ReportItem;
import admin.model.ReportType;
import admin.model.ReportTypeEnum;
import admin.model.req.ReportFilterRq;
import admin.model.req.ReportRq;
import admin.service.ReportService;
import core.constant.WebConstant;
import core.exception.DdException;
import domain.attribute.model.rq.PageRq;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public long getPageCount() {
		long pageNum = 0;
		pageNum = (long) Math.ceil((double) reportDao.getPageCount()
				/ WebConstant.ADMIN_REPORT_PER_PAGE);
		return pageNum;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<ReportItem> getByPage(PageRq pageRq) {
		List<ReportItem> items = reportDao.getByPage(pageRq.getPage()
				* WebConstant.ADMIN_REPORT_PER_PAGE,
				WebConstant.ADMIN_REPORT_PER_PAGE);
		return items;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void process(int reportId) {
		reportDao.process(reportId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(int reportId) {
		reportDao.delete(reportId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<ReportItem> getPendings() {
		List<ReportItem> reportItemList = reportDao.getPendings();
		return reportItemList;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<ReportItem> getResolveds() {
		List<ReportItem> reportItemList = reportDao.getResolveds();
		return reportItemList;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public PageBuilder<ReportItem> getByFilter(ReportFilterRq reportRq) {
		return reportDao.getByFilter(reportRq);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public long insert(ReportRq reportRq) throws DdException {
		validate(reportRq);
		return reportDao.insert(reportRq);
	}

	private void validate(ReportRq report) throws DdException {
		if (report.getDcrp().isEmpty()) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					ReportRq.DESCRIPTION_EMPTY_CODE, ReportRq.DESCRIPTION_EMPTY);
		}
		if (report.getDcrp().length() > 500) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					ReportRq.DESCRIPTION_TOO_LONG_CODE,
					ReportRq.DESCRIPTION_TOO_LONG);
		}
		if (reportDao.isDuplicate(report)) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					ReportRq.DUPLICATE_CODE, ReportRq.DUPLICATE);
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<ReportType> getType() {
		List<ReportType> list = new ArrayList<ReportType>();
		list.add(new ReportType(-1, "All"));
		for (ReportTypeEnum type : ReportTypeEnum.values()) {
			list.add(new ReportType(type.ordinal(), type.name().toLowerCase()));
		}
		return list;
	}
}
