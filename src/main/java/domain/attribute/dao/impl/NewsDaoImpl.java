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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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

import core.constant.WebConstant;
import domain.attribute.dao.NewsDao;
import domain.attribute.model.News;
import domain.attribute.model.NewsView;

@Repository("newsDao")
public class NewsDaoImpl extends JdbcDaoSupport implements NewsDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	private String query = "SELECT News.Id as Id, Places.Id as PlaceId, Avatar, Places.Name as PlaceName, "
			+ "News.CreateDate, Tags.Name as TagName, News.Id as NewsId, News.Dcrp as Dcrp, Title, Cont, "
			+ "Promotion, Event , CreateDate, News.Image "
			+ "FROM Places INNER JOIN NEWS ON Places.Id = News.Place "
			+ "LEFT JOIN Tags ON Places.Tag = Tags.Id ";

	private String order = "ORDER BY CreateDate DESC LIMIT ?, ? ";

	@Override
	public long insert(final News news, final long placeId) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO News(Title, Dcrp, Cont, Promotion, Event, CreateDate, Place)"
								+ "VALUES(?,?,?,?,?,NOW(),?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, news.getTitle());
				pstmt.setString(2, news.getDesc());
				pstmt.setString(3, news.getCont());
				pstmt.setBoolean(4, news.isPromotion());
				pstmt.setBoolean(5, news.isEvent());
				pstmt.setLong(6, placeId);
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(News news, long newsId) {
		String sql = "";
		Calendar cal = Calendar.getInstance();
		long dis = cal.getTimeInMillis() - news.getDate().getTime();
		dis /= (24 * 60 * 60 * 1000);

		sql = "UPDATE News SET Title=?, Dcrp=?, Cont=?, Promotion=?, Event=? ";
		if (dis > WebConstant.NEWS_UPDATE_DAY) {
			sql += ", CreateDate=NOW()";
		}
		sql += " WHERE Id = ?";
		getJdbcTemplate().update(sql, news.getTitle(), news.getDesc(),
				news.getCont(), news.isPromotion(), news.isEvent(), newsId);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM News WHERE Id=?";
		getJdbcTemplate().update(sql, id);
	}

	@Override
	public News getByPlace(long placeId) {
		String sql = "SELECT Id, Title, Dcrp, Cont, Promotion, Event, CreateDate, Image "
				+ "FROM News WHERE Place = ?";
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql,
				placeId);
		return result.isEmpty() ? null : newsRowMapper(result.get(0));
	}

	private List<NewsView> get(int from, int count, String condition) {
		String sql = query + condition + order;
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql,
				from * WebConstant.NEWS_PER_PAGE, count);
		List<NewsView> views = new ArrayList<NewsView>();
		for (Map<String, Object> row : result) {
			views.add(viewsRowMapper(row, from));
		}
		return views;
	}

	@Override
	public List<NewsView> getByCategory(int category, int from, int count) {
		return get(from, count, "WHERE Places.BusinessType = " + category + " ");
	}

	@Override
	public List<NewsView> getNews(int from, int count) {
		return get(from, count, "");
	}

	@Override
	public List<NewsView> getDiscounts(int from, int count) {
		return get(from, count, "WHERE Discount = TRUE ");
	}

	@Override
	public List<NewsView> getNotifies(int from, int count) {
		return get(from, count, "WHERE Notify = TRUE ");
	}

	@Override
	public List<NewsView> getEvents(int from, int count) {
		return get(from, count, "WHERE Event = TRUE ");
	}

	@Override
	public void updateImage(long newsId, long imageId) {
		String sql = "UPDATE News SET Image=? ";
		sql += " WHERE Id = ?";
		getJdbcTemplate().update(sql, imageId, newsId);
	}

	public long getImage(long newsId) {
		String sql = "SELECT Image FROM News WHERE Id=?";
		return getJdbcTemplate().queryForLong(sql, newsId);
	}

	private News newsRowMapper(Map<String, Object> row) {
		News news = new News((Long) row.get("id"), (String) row.get("Title"),
				(String) row.get("Dcrp"), (String) row.get("Cont"),
				(Boolean) row.get("Promotion"), (Boolean) row.get("Event"),
				(String) row.get("Image"));
		news.setDate((Date) row.get("CreateDate"));
		return news;
	}

	private NewsView viewsRowMapper(Map<String, Object> row, int from) {
		NewsView view = new NewsView();
		view.setId((Long) row.get("Id"));
		view.setPlaceId((Long) row.get("PlaceId"));
		view.setAvatar((Long) row.get("Avatar"));
		view.setPlaceName((String) row.get("PlaceName"));
		view.setTagName((String) row.get("TagName"));
		view.setTitle((String) row.get("Title"));
		view.setDcrp((String) row.get("Dcrp"));
		view.setCont((String) row.get("Cont"));
		view.setDate((Date) row.get("CreateDate"));
		view.setPromotion((Boolean) row.get("Promotion"));
		view.setEvent((Boolean) row.get("Event"));
		view.setImage((String) row.get("Image"));
		view.setPageNum(from);
		return view;
	}

}
