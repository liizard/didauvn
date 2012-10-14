package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.model.PageBuilder;
import admin.model.ReportItem;
import admin.model.ReportType;
import admin.model.req.ReportFilterRq;
import admin.model.req.ReportRq;
import admin.service.ReportService;
import core.exception.DdException;
import domain.attribute.model.rq.PageRq;

@RequestMapping("admin/data/report")
@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public void submitReport(@RequestBody ReportRq reportRq) throws DdException {
		reportService.insert(reportRq);
	}
	
	// Get number of Report pages
	@RequestMapping(value = "/getpagecount", method = RequestMethod.POST)
	@ResponseBody
	public long getPageCount() {
		return reportService.getPageCount();
	}
	
	// Get Reports by Page
	@RequestMapping(value = "/getbypage", method = RequestMethod.POST)
	@ResponseBody
	public List<ReportItem> getReportsByPage(@RequestBody PageRq pageRq) {
		return reportService.getByPage(pageRq);
	}

	@RequestMapping(value = "/getbyfilter", method = RequestMethod.POST)
	@ResponseBody
	public PageBuilder<ReportItem> getReportItems(@RequestBody ReportFilterRq reportRq) {
		return reportService.getByFilter(reportRq);
	}

	@RequestMapping(value = "/template", method = RequestMethod.GET)
	@ResponseBody
	public ReportFilterRq getReportRequestTemplate() {
		return new ReportFilterRq();
	}
	
	@RequestMapping(value = "/reporttype", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportType> getReportType() {
		return reportService.getType();
	}

	/*
	 * @RequestMapping(value = "/get/pending", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<ReportItem> getPendingReportItems() { return
	 * reportService.getPendingReportItems(); }
	 * 
	 * @RequestMapping(value = "/get/resolved", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<ReportItem> getResolvedReportItems() { return
	 * reportService.getResolvedReportItems(); }
	 */

	@RequestMapping(value = "/process/{reportId}", method = RequestMethod.POST)
	@ResponseBody
	public void processReportItem(@PathVariable("reportId") int reportId) {
		reportService.process(reportId);
	}

	@RequestMapping(value = "/delete/{reportId}", method = RequestMethod.POST)
	@ResponseBody
	public void deleteReportItem(@PathVariable("reportId") int reportId) {
		reportService.delete(reportId);
	}

}
