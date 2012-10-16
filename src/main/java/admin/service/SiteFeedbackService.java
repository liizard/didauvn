package admin.service;

import java.util.List;

import admin.model.SiteFeedback;

public interface SiteFeedbackService {
	public List<SiteFeedback> getAllSiteFeedbacks();
	public void deleteSiteFeedbacks(SiteFeedback[] siteFeedbackArr);
	public void addSiteFeedback(SiteFeedback siteFeedback);
}
