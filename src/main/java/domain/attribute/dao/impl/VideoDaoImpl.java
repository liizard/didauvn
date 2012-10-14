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

import domain.attribute.dao.VideoDao;
import domain.attribute.model.Video;

@Repository
public class VideoDaoImpl extends JdbcDaoSupport implements VideoDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public long insert(final Video video) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Videos(caption,href,place) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, video.getCaption());
				pstmt.setString(2, video.getHref());
				pstmt.setLong(3, video.getPlace());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(Video video) {
		String sql = "UPDATE Videos SET caption=? WHERE id=?";
		getJdbcTemplate().update(sql,
				new Object[] { video.getCaption(), video.getId() });
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM Videos WHERE id=?";
		getJdbcTemplate().update(sql, new Object[] { id });
	}

	public Video rowMapperVideo(Map<String, Object> row) {
		Video video = new Video();
		video.setId(((BigInteger) row.get("Id")).longValue());
		video.setCaption((String) row.get("caption"));
		video.setHref((String) row.get("href"));
		video.setPlace((Long) row.get("place"));
		return video;
	}

	@Override
	public Video findById(long id) {
		String sql = "SELECT * FROM Videos WHERE id=?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { id });
		return rows.isEmpty() ? null : rowMapperVideo(rows.get(0));
	}

	@Override
	public List<Video> get(int from, int count) {
		String sql = "SELECT * FROM Videos ORDER BY Id DESC LIMIT ?,? ";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { from, count });
		List<Video> videos = new ArrayList<Video>();
		for (Map<String, Object> row : rows) {
			videos.add(rowMapperVideo(row));
		}
		return videos;
	}

	@Override
	public List<Video> get() {
		String sql = "SELECT * FROM Videos ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<Video> videos = new ArrayList<Video>();
		for (Map<String, Object> row : rows) {
			videos.add(rowMapperVideo(row));
		}
		return videos;
	}
	
	@Override
	public int countVideos(long placeId) {
		String sql = "SELECT COUNT(Id) FROM Videos WHERE Place = ?";
		return getJdbcTemplate().queryForInt(sql, placeId);
	}

	@Override
	public List<Video> getVideos(long placeId) {
		String sql = "SELECT * FROM Videos WHERE Place = ? ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId);
		List<Video> videos = new ArrayList<Video>();
		for (Map<String, Object> row : rows) {
			videos.add(rowMapperVideo(row));
		}
		return videos;
	}

	// get More Videos
	@Override
	public List<Video> getMoreVideos(long placeId, long lastId, int count) {
		String sql = "SELECT * FROM Videos WHERE Place = ? ";
		if (lastId != 0) {
			sql += "And Id < " + lastId + " ";
		}
		sql += "ORDER BY Id DESC LIMIT ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId, count);
		List<Video> videos = new ArrayList<Video>();
		for (Map<String, Object> row : rows) {
			videos.add(rowMapperVideo(row));
		}
		return videos;
	}

	// Get Videos By Page
	@Override
	public List<Video> getVideosByPage(long placeId, int page, int count) {
		String sql = "SELECT * FROM Videos WHERE Place = ? ORDER BY Id DESC LIMIT ?, ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId, page, count);
		List<Video> videos = new ArrayList<Video>();
		for (Map<String, Object> row : rows) {
			videos.add(rowMapperVideo(row));
		}
		return videos;
	}
	
}
