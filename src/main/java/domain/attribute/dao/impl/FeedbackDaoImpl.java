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
package domain.attribute.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.FeedbackDao;
import domain.attribute.model.Feedback;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends JdbcDaoSupport implements FeedbackDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public List<Feedback> getFeedbackViews(long placeId) {
		String sql = "SELECT f.Id, f.Content, f.CreateDate, f.User, u.Name, u.Avatar "
				+ "FROM Feedbacks f INNER JOIN Users u ON f.User=u.Id "
				+ "WHERE Place=? ORDER BY f.ID DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId);
		List<Feedback> feedbackViews = new ArrayList<Feedback>();
		Feedback feedbackView;
		for (Map<String, Object> row : rows) {
			feedbackView = new Feedback(
					((BigInteger) row.get("Id")).longValue(),
					(String) row.get("Content"), (Date) row.get("CreateDate"),
					(Long) row.get("User"), (String) row.get("Name"),
					(String) row.get("Avatar"));
			feedbackViews.add(feedbackView);
		}
		return feedbackViews;
	}

	@Override
	public long insert(final Feedback feedbackView, final long placeId) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con
						.prepareStatement(
								"INSERT INTO Feedbacks(content,createDate,place,user) VALUES(?,NOW(),?,?)",
								Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, feedbackView.getCont());
				pstmt.setLong(2, placeId);
				pstmt.setLong(3, feedbackView.getUserId());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean checkPlace(long id) {
		String sql = "Select count(*) from places WHERE Id = ?";
		int result = getJdbcTemplate().queryForInt(sql, id);
		if (result > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteFeedback(long id, long userId) {
		String sql = "Delete from feedbacks WHERE Id = ? AND User = ?";
		int result = getJdbcTemplate().update(sql, id, userId);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Feedback> getMoreFeedbacks(long placeId, long lastId, int count) {
		String sql = "SELECT f.Id, f.Content, f.CreateDate, f.User, u.Name, u.Avatar "
				+ "FROM Feedbacks f INNER JOIN Users u ON f.User=u.Id "
				+ "WHERE Place=? ";
		if (lastId != 0) {
			sql += "And f.Id < " + lastId + " ";
		}
		sql += "ORDER BY f.Id DESC LIMIT ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId, count);
		List<Feedback> feedbackViews = new ArrayList<Feedback>();
		for (Map<String, Object> row : rows) {
			feedbackViews.add(rowMapper(row));
		}
		return feedbackViews;
	}

	private Feedback rowMapper(Map<String, Object> row) {
		Feedback feedbackView = new Feedback(
				((BigInteger) row.get("Id")).longValue(),
				(String) row.get("Content"), (Date) row.get("CreateDate"),
				(Long) row.get("User"), (String) row.get("Name"),
				(String) row.get("Avatar"));
		return feedbackView;
	}

}
