package admin.dao;

import java.util.List;

import admin.model.SiteFeedback;

public interface SiteFeedbacksDao {
	public List<SiteFeedback> getAllSiteFeedbacks();
	public void deleteSiteFeedbacks(SiteFeedback[] siteFeedbackArr);
	public void addSiteFeedback(SiteFeedback siteFeedback);
	
}
