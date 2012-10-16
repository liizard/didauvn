package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.model.SiteFeedback;
import admin.service.SiteFeedbackService;

@RequestMapping("admin/data/sitefeedback")
@Controller
public class SiteFeedbacksController {
	@Autowired
	private SiteFeedbackService siteFeedbackService;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<SiteFeedback> getPageCount() {
		return siteFeedbackService.getAllSiteFeedbacks();
	}
	
}
