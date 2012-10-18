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
package domain.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import domain.user.dao.UserDao;
import domain.user.model.Gender;
import domain.user.model.User;
import domain.user.model.UserGeneral;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	// Insert to User
	@Override
	public long insert(final User user) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con
						.prepareStatement(
								"INSERT INTO Users(email,name,gender,birthday,status,createDate) VALUES(?,?,?,?,?,NOW())",
								Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getEmail());
				pstmt.setString(2, user.getName());
				pstmt.setInt(3,
						(Gender.valueOf(user.getGender().toUpperCase()))
								.ordinal());
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					pstmt.setDate(4,
							new java.sql.Date(df.parse(user.getBirthday())
									.getTime()));
				} catch (ParseException e) {
				}
				// status: 1 = not enable
				pstmt.setInt(5, 1);
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	// Insert to User
	@Override
	public long insertForFB(final User user) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con
						.prepareStatement(
								"INSERT INTO Users(email,name,status,createDate) VALUES(?,?,?,NOW())",
								Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, user.getEmail());
				pstmt.setString(2, user.getName());
				pstmt.setInt(3, 1);
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	// Update User
	@Override
	public void update(User user) throws ParseException {
		String sql = "UPDATE Users SET name=?,gender=?,birthday=?,rStatus=?,hometown=? WHERE id=?";
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date date = new java.sql.Date(df.parse(user.getBirthday())
				.getTime());
		getJdbcTemplate().update(sql, user.getName(),
				Gender.valueOf(user.getGender().toUpperCase()).ordinal(), date,
				user.getrStatus(), user.getHomeTown(), user.getUid());
	}

	// Delete from User
	@Override
	public void delete(long id) {
		String sql = "DELETE FROM Users WHERE id=?";
		getJdbcTemplate().update(sql, id);
	}

	// Find User by ID
	@Override
	public User findById(long id) {
		String sql = "SELECT * FROM Users WHERE id=?";
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(sql, id);
		return rows.isEmpty() ? null : rowMapperUser(rows.get(0));
	}

	// Get limit List of User
	@Override
	public List<User> get(int from, int count) {
		String sql = "SELECT * FROM Users ORDER BY id DESC LIMIT ?,? ";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				from, count);
		List<User> users = new ArrayList<User>();
		for (Map<String, Object> row : rows) {
			users.add(rowMapperUser(row));
		}
		return users;
	}

	// Get all List of User
	@Override
	public List<User> get() {
		String sql = "SELECT * FROM Users ";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<User> users = new ArrayList<User>();
		for (Map<String, Object> row : rows) {
			users.add(rowMapperUser(row));
		}
		return users;
	}

	// Get count of User
	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM Users ";
		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public void insertPassword(long id, String password) {
		String sql = "INSERT INTO registerusers VALUES(?,?)";
		getJdbcTemplate().update(sql, id, password);
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "Select * from registerusers ru INNER JOIN (SELECT * from users WHERE email=?) u ON ru.User = u.Id";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				email);
		return rows.isEmpty() ? null : rowMapperUser(rows.get(0));
	}

	@Override
	public boolean checkEmail(String email) {
		String sql = "Select count(*) from users WHERE email=?";
		int result = getJdbcTemplate().queryForInt(sql, email);
		if (result == 0)
			return true;
		else
			return false;
	}

	@Override
	public void changePassword(long id, String password) {
		String sql = "Update registerusers SET password = ? WHERE User = ?";
		getJdbcTemplate().update(sql, password, id);
	}

	@Override
	public UserGeneral findUserViewById(long id) {
		String sql = "SELECT id,name,gender,birthday,createdate from Users WHERE id=?";
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(sql, id);
		UserGeneral userView = null;
		if (!rows.isEmpty()) {
			userView = new UserGeneral();
			userView.setUid((Long) rows.get(0).get("id"));
			userView.setName((String) rows.get(0).get("name"));
			userView.setGender((Gender.values()[(Integer) rows.get(0).get(
					"Gender")]).name().toLowerCase());
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			userView.setBirthday(df.format((Date) rows.get(0).get("birthday")));
			userView.setCreatedate(df.format((Date) rows.get(0).get(
					"createdate")));
			sql = "select providerid,profileurl from userconnection where userid = ?";
			rows = getJdbcTemplate().queryForList(sql, id);
			if (rows.isEmpty()) {
				return userView;
			} else {
				for (int i = 0; i < rows.size(); i++) {
					if (((String) rows.get(i).get("providerId"))
							.equalsIgnoreCase("facebook")) {
						userView.setProfileFB((String) rows.get(i).get(
								"profileUrl"));
						continue;
					}
					if (((String) rows.get(i).get("providerId"))
							.equalsIgnoreCase("twitter")) {
						userView.setProfileTwitter((String) rows.get(i).get(
								"profileUrl"));
						continue;
					}
				}
				return userView;
			}
		} else
			return null;
	}

	@Override
	public long getImage(long userId) {
		String sql = "SELECT Avatar FROM Users WHERE Id=?";
		return getJdbcTemplate().queryForLong(sql, userId);
	}

	@Override
	public void updateImage(long userId, long imageId) {
		String sql = "UPDATE Users SET Avatar=? WHERE Id=?";
		getJdbcTemplate().update(sql, imageId, userId);
	}

	private User rowMapperUser(Map<String, Object> row) {
		User user = new User();
		user.setUid((Long) row.get("id"));
		user.setEmail((String) row.get("email"));
		user.setPassword((String) row.get("password"));
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (row.get("birthday") != null)
			user.setBirthday(df.format((Date) row.get("birthday")));
		if (row.get("Gender") != null)
			user.setGender((Gender.values()[(Integer) row.get("Gender")])
					.name().toLowerCase());
		user.setName((String) row.get("name"));
		user.setStatus((Integer) row.get("status"));
		user.setCreateDate((Date) row.get("createDate"));
		user.setAvatar((String) row.get("Avatar"));
		return user;
	}

	@Override
	public void insertToken(long userId, String token) {
		String sql = "INSERT INTO useractivation VALUES(?,?,now())";
		getJdbcTemplate().update(sql, userId, token);
	}

	@Override
	public boolean isValidActivation(long userId, String token) {
		String sql = "Select count(*) from useractivation WHERE user = ? AND token = ?";
		int result = getJdbcTemplate().queryForInt(sql, userId, token);
		if (result == 1)
			return true;
		return false;
	}

	@Override
	public void activateUser(long userId) {
		String sql = "Update users SET status = 1 WHERE id = ?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public void deactivateUser(long userId) {
		String sql = "Update users SET status = 0 WHERE id = ?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public void deleteInvalidActivation(long userId) {
		String sql = "Delete From useractivation WHERE user = ?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public void truncateUserActivationTable() {
		String sql = "Delete From useractivation WHERE CreateDate <= DATE_SUB(NOW(), INTERVAL 1 DAY)";
		getJdbcTemplate().update(sql);
	}

	@Override
	public boolean isAdmin(long id) {
		String sql = "SELECT COUNT(*) FROM Admins WHERE User=?";
		int count = getJdbcTemplate().queryForInt(sql, id);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
