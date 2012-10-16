package admin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.model.PlaceTag;

import admin.dao.SiteFeedbacksDao;
import admin.model.SiteFeedback;

@Repository("siteFeedbacksDao")
public class SiteFeedbacksDaoImpl extends JdbcDaoSupport implements SiteFeedbacksDao {
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}
	@Override
	public List<SiteFeedback> getAllSiteFeedbacks() {
		ArrayList<SiteFeedback> list = new ArrayList<SiteFeedback>();
		String sql = "SELECT users.name, sitefeedbacks.id, sitefeedbacks.content, sitefeedbacks.createDate FROM users, sitefeedbacks "
					+	" WHERE users.id = sitefeedbacks.userId ";
		List<Map<String, Object>> result;
		result = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> m : result) {
			list.add(rowMapper(m));
		}
		return list;
	}

	@Override
	public void deleteSiteFeedbacks(SiteFeedback[] siteFeedbackArr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSiteFeedback(SiteFeedback siteFeedback) {
		// TODO Auto-generated method stub
		
	}
	
	private SiteFeedback rowMapper(Map<String, Object> row) {
		SiteFeedback siteFeedback = new SiteFeedback();
		siteFeedback.setId((Long) row.get("Id"));
		siteFeedback.setContent((String) row.get("content"));
		siteFeedback.setCreateDate((Date) row.get("createDate"));
		siteFeedback.setUserName((String) row.get("name"));		
		return siteFeedback;
	}

}
