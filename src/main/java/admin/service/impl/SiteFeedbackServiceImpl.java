package admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.SiteFeedbacksDao;
import admin.model.SiteFeedback;
import admin.service.SiteFeedbackService;

@Service("siteFeedbackService")
public class SiteFeedbackServiceImpl implements SiteFeedbackService{
	
	@Autowired
	private SiteFeedbacksDao siteFeedbacksDao;
	
	@Override
	public List<SiteFeedback> getAllSiteFeedbacks() {
		
		// TODO Auto-generated method stub
		return siteFeedbacksDao.getAllSiteFeedbacks();
	}

	@Override
	public void deleteSiteFeedbacks(SiteFeedback[] siteFeedbackArr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSiteFeedback(SiteFeedback siteFeedback) {
		// TODO Auto-generated method stub
		
	}
	
}
