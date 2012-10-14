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
package domain.place.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.place.dao.PlaceSettingDao;
import domain.place.model.PlaceManager;

@Repository("placeSettingDao")
public class PlaceSettingDaoImpl extends JdbcDaoSupport implements
		PlaceSettingDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	public PlaceManager addManager(String email, long placeId) {
		String sql = "SELECT Id, Email, Name FROM Users WHERE Email = ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				email);
		if (!rows.isEmpty()) {
			PlaceManager manager = rowMapperUser(rows.get(0));
			String sql2 = "Insert into managers values (?,?)";
			getJdbcTemplate().update(sql2, placeId, manager.getUserId());
			return manager;
		} else {
			return null;
		}
	}

	public List<PlaceManager> getAllManagersByPlace(long placeId) {
		ArrayList<PlaceManager> managers = new ArrayList<PlaceManager>();
		String sql = "select u.id,u.name,u.email from managers m,users u WHERE m.user = u.id AND m.place = ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				placeId);
		for (Map<String, Object> row : rows) {
			managers.add(mapToManager(row));
		}
		return managers;
	}

	private PlaceManager mapToManager(Map<String, Object> row) {
		return (row == null || row.isEmpty()) ? null : new PlaceManager(
				((Long) row.get("id")), (String) row.get("email"),
				(String) row.get("name"));
	}

	public PlaceManager rowMapperUser(Map<String, Object> row) {
		PlaceManager placeManager = new PlaceManager();
		placeManager.setUserId((Long) row.get("id"));
		placeManager.setEmail((String) row.get("email"));
		placeManager.setName((String) row.get("name"));
		return placeManager;
	}

	@Override
	public void deleteManagers(List<PlaceManager> managers, long placeId) {
		StringBuilder string = new StringBuilder();
		for (PlaceManager m : managers) {
			if (m.isChecked()) {
				string.append(m.getUserId());
				string.append(',');
			}
		}
		string.deleteCharAt(string.length() - 1);
		String sql = "DELETE FROM Managers WHERE place=" + placeId
				+ " AND user in (" + string + ")";
		getJdbcTemplate().update(sql);
	}
}
