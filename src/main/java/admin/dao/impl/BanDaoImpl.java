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
package admin.dao.impl;

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

import admin.dao.BanDao;
import admin.model.Ban;

@Repository
public class BanDaoImpl extends JdbcDaoSupport implements BanDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public Ban getBan(long id) {
		String sql = "SELECT Id, User, BanDate, NoDay, Reason, Admin "
				+ "FROM Bans WHERE Id=?";
		Map<String, Object> row = getJdbcTemplate().queryForMap(sql, id);
		return mapToBan(row);
	}

	@Override
	public List<Ban> getBans() {
		String sql = "SELECT Id, User, BanDate, NoDay, Reason, Admin "
				+ "FROM Bans ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<Ban> bans = new ArrayList<Ban>();

		for (Map<String, Object> row : rows) {
			bans.add(mapToBan(row));
		}
		return bans;
	}
	
	// Get Image By Page
		@Override
		public List<Ban> getBansByPage(int page, int count) {
			String sql = "SELECT Id, User, BanDate, NoDay, Reason, Admin "
				+ "FROM Bans ORDER BY Id DESC LIMIT ?,?";
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, page, count);
			List<Ban> bans = new ArrayList<Ban>();

			for (Map<String, Object> row : rows) {
				bans.add(mapToBan(row));
			}
			return bans;
		}


		@Override
		public long countBans() {
			long count = 0;
			String sql = "SELECT COUNT(Id) AS count FROM Bans";
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

			if (rows.isEmpty()) {
				return 0;
			} else {
				count = (Long) rows.get(0).get("count");
			}
			return count;
		}

	@Override
	public long insertBan(final Ban ban) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Bans(User, BanDate, NoDay, Reason, Admin)"
								+ "VALUES(?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setLong(1, ban.getUserId());
				pstmt.setDate(2, new java.sql.Date(ban.getBanDate().getTime()));
				pstmt.setLong(3, ban.getNoDay());
				pstmt.setString(4, ban.getReason());
				pstmt.setLong(5, ban.getAdminId());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updateBan(Ban ban) {
		String sql = "UPDATE Bans SET BanDate=?, NoDay=?, Reason=? WHERE Id=?";
		getJdbcTemplate().update(sql,
				new java.sql.Date(ban.getBanDate().getTime()), ban.getNoDay(),
				ban.getReason(), ban.getId());
	}

	@Override
	public void deleteBan(long id) {
		String sql = "DELETE FROM Bans WHERE Id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public boolean checkDupUser(long id) {
		String sql = "Select count(*) from Bans b where DATE_ADD(BanDate, INTERVAL b.NoDay DAY) >= CURDATE() and user=?";
		int result = getJdbcTemplate().queryForInt(sql, id);
		if (result == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public void updateUserStatusByBans() {
		String sql = "update users set status = 1 where Id in "
				+ "(select user from bans b where DATE_ADD(BanDate, INTERVAL "
				+ "b.NoDay"
				+ " DAY) < CURDATE())";
		getJdbcTemplate().update(sql);
		
		sql = "update users set status = 0 where Id in "
				+ "(select user from bans b where DATE_ADD(BanDate, INTERVAL "
				+ "b.NoDay"
				+ " DAY) >= CURDATE())";
		getJdbcTemplate().update(sql);
	}
	
	private Ban mapToBan(Map<String, Object> row) {
		return (row == null || row.isEmpty()) ? null : new Ban(
				(Long) row.get("Id"), (Long) row.get("User"),
				(Date) row.get("BanDate"), (Long) row.get("NoDay"),
				(String) row.get("Reason"), (Long) row.get("Admin"));
	}

}
