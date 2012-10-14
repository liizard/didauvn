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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.PlaceTagDao;
import domain.attribute.model.PlaceTag;

@Repository("placeTagDao")
public class PlaceTagDaoImpl extends JdbcDaoSupport implements PlaceTagDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	private PlaceTag rowMapper(Map<String, Object> row) {
		PlaceTag tag = new PlaceTag();
		tag.setId((Long) row.get("Id"));
		tag.setName((String) row.get("Name"));
		return tag;
	}

	@Override
	public List<PlaceTag> getPlaceTags(long placeId) {
		String sql = "SELECT Id, Name "
				+ " FROM Tags INNER JOIN PlaceTags ON Tags.Id = PlaceTags.Tag "
				+ " WHERE PlaceTags.Place = ?";
		List<Map<String, Object>> result;

		result = getJdbcTemplate().queryForList(sql, placeId);

		List<PlaceTag> tags = new ArrayList<PlaceTag>();
		for (Map<String, Object> m : result) {
			tags.add(rowMapper(m));
		}
		return tags;
	}

	@Override
	public PlaceTag getMainTag(long placeId) {
		String sql = "SELECT Tags.Id, Tags.Name "
				+ " FROM Tags INNER JOIN Places ON Tags.Id = Places.Tag "
				+ " WHERE Places.Id = ?";
		List<Map<String, Object>> result;

		result = getJdbcTemplate().queryForList(sql, placeId);
		return result.isEmpty() ? null : rowMapper(result.get(0));
	}

	@Override
	public void updateMainTag(long placeId, long tagId) {
		String sql = "UPDATE Places SET Tag = ? WHERE Id = ?";
		getJdbcTemplate().update(sql, tagId, placeId);
	}

	@Override
	public void insertPlaceTags(long placeId, List<PlaceTag> tags) {
		int size = tags.size();
		String[] sqls = new String[size];
		for (int i = 0; i < size; i++) {
			sqls[i] = "INSERT INTO PlaceTags (Place, Tag) " + "VALUES("
					+ placeId + ", " + tags.get(i).getId() + ")";
		}
		getJdbcTemplate().batchUpdate(sqls);
	}

	@Override
	public void deletePlaceTags(long placeId, List<PlaceTag> tags) {
		int size = tags.size();
		String[] sqls = new String[size];
		for (int i = 0; i < size; i++) {
			sqls[i] = "DELETE FROM PlaceTags WHERE " + "Place = " + placeId
					+ " AND " + "Tag = " + tags.get(i).getId();
		}
		getJdbcTemplate().batchUpdate(sqls);
	}

	@Override
	public List<PlaceTag> getTagsByRequest(String request) {
		String sql = "(SELECT Id, Name, Rank FROM Tags WHERE Name LIKE '"
				+ request + "%' ) " + "UNION "
				+ "(SELECT Id, name, Rank FROM tags WHERE Name LIKE '%"
				+ request + "%' ) " + "ORDER BY Rank DESC LIMIT 0,10";
		List<Map<String, Object>> result;
		result = getJdbcTemplate().queryForList(sql);

		List<PlaceTag> tags = new ArrayList<PlaceTag>();
		for (Map<String, Object> m : result) {
			tags.add(rowMapper(m));
		}
		return tags;
	}

	@Override
	public void updateRank(final PlaceTag[] tags) {
		String sql = "UPDATE Tags SET Rank = Rank + 1 WHERE Id = ?";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				PlaceTag tag = tags[i];
				ps.setLong(1, tag.getId());
			}

			@Override
			public int getBatchSize() {
				return tags.length;
			}
		});
	}
}
