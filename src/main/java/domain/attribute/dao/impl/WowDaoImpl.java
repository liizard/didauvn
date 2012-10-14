package domain.attribute.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.WowDao;

@Repository
public class WowDaoImpl extends JdbcDaoSupport implements WowDao {

	@Autowired
	public WowDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void insert(long placeid, long userid) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO subscribers VALUES(?,?)";
		getJdbcTemplate().update(sql, placeid, userid);
	}

	@Override
	public List<Long> getUsersWow(long placeid) {
		String sql = "SELECT User from subscribers WHERE Place = ?";
		List<Long> userids = getJdbcTemplate().queryForList(sql, Long.class,
				placeid);
		return userids;
	}

	@Override
	public void delete(long placeid, long userid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM subscribers WHERE Place = ? and User = ? ";
		getJdbcTemplate().update(sql, placeid, userid);
	}

}
