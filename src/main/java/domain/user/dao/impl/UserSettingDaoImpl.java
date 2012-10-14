package domain.user.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import core.enumeration.LanguageEnum;
import domain.user.dao.UserSettingDao;

@Repository
public class UserSettingDaoImpl extends JdbcDaoSupport implements
		UserSettingDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public String getLang(long id) {
		String sql = "SELECT language FROM UserSettings where user=? ";
		return getJdbcTemplate().queryForObject(sql, String.class, id);

	}

	@Override
	public void updateLang(long id, LanguageEnum lang) {
		String sql = "UPDATE UserSettings SET language=? WHERE user=?";
		getJdbcTemplate().update(sql, lang.toString().toLowerCase(), id);
	}

	@Override
	public List<Long> getPlaceOwners(long userId) {
		String sql = "SELECT Id from Places WHERE Owner = ?";
		return getJdbcTemplate().queryForList(sql, Long.class, userId);
	}

	@Override
	public List<Long> getPlaceManagers(long userId) {
		String sql = "Select Place from Managers WHERE User = ?";
		return getJdbcTemplate().queryForList(sql, Long.class, userId);
	}

	@Override
	public List<Long> getPlaceWows(long userId) {
		// TODO Auto-generated method stub
		String sql = "Select Place from subscribers WHERE User = ?";
		return getJdbcTemplate().queryForList(sql, Long.class, userId);
	}

}
