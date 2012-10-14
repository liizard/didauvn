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

import domain.attribute.dao.ImageDao;
import domain.attribute.model.Image;

@Repository("imageDao")
public class ImageDaoImpl extends JdbcDaoSupport implements ImageDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public Image getImage(long id) {
		String sql = "SELECT Id, Caption, Place " + "FROM Images WHERE Id=?";
		Map<String, Object> row = getJdbcTemplate().queryForMap(sql, id);
		return mapToImage(row);
	}

	@Override
	public List<Image> getImages() {
		String sql = "SELECT Id, Caption, Place "
				+ "FROM Images ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<Image> images = new ArrayList<Image>();

		for (Map<String, Object> row : rows) {
			images.add(mapToImage(row));
		}
		return images;
	}

	@Override
	public List<Image> getImages(long placeId) {
		String sql = "SELECT Id, Caption, Place "
				+ "FROM Images where Place = ? ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId);
		List<Image> images = new ArrayList<Image>();

		for (Map<String, Object> row : rows) {
			images.add(mapToImage(row));
		}
		return images;
	}

	// get More Images
	@Override
	public List<Image> getMoreImages(long placeId, long lastId, int count) {
		String sql = "SELECT Id, Caption, Place FROM Images where Place = ? ";
		if (lastId != 0) {
			sql += "And Id < " + lastId + " ";
		}
		sql += "ORDER BY Id DESC LIMIT ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId, count);
		List<Image> images = new ArrayList<Image>();

		for (Map<String, Object> row : rows) {
			images.add(mapToImage(row));
		}
		return images;

	}
	
	// Get Image By Page
	@Override
	public List<Image> getImagesByPage(long placeId, int page, int count) {
		String sql = "SELECT Id, Caption, Place "
				+ "FROM Images where Place = ? ORDER BY Id DESC LIMIT ?,?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId, page, count);
		List<Image> images = new ArrayList<Image>();

		for (Map<String, Object> row : rows) {
			images.add(mapToImage(row));
		}
		return images;
	}


	@Override
	public long countImages(long placeId) {
		long count = 0;
		String sql = "SELECT COUNT(Id) AS count FROM images WHERE place = ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId);

		if (rows.isEmpty()) {
			return 0;
		} else {
			count = (Long) rows.get(0).get("count");
		}
		return count;
	}

	@Override
	public long insertImage(final Image image) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Images(Caption, Place)" + "VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, image.getCaption());
				pstmt.setLong(2, image.getPlaceId());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updateImage(Image image) {
		String sql = "UPDATE Images SET Caption=? WHERE Id=?";
		getJdbcTemplate().update(sql, image.getCaption(), image.getId());
	}

	@Override
	public void deleteImage(long id) {
		String sql = "DELETE FROM Images WHERE Id=?";
		getJdbcTemplate().update(sql, id);
	}

	private Image mapToImage(Map<String, Object> row) {
		return (row == null || row.isEmpty()) ? null : new Image(
				((BigInteger) row.get("Id")).longValue(),
				(String) row.get("Caption"), (Long) row.get("Place"));
	}

}
