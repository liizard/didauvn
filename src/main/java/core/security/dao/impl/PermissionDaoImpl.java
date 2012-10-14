package core.security.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import core.security.dao.PermissionDao;

@Repository
public class PermissionDaoImpl extends JdbcDaoSupport implements PermissionDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}
	
	@Override
	public long checkNewsPlaceOwner(long newsId) {
		String sql = "SELECT Place from news WHERE id = ?";
		return getJdbcTemplate().queryForLong(sql, newsId);
	}
	
	@Override
	public long checkImgPlaceOwner(long imageId) {
		String sql = "SELECT Place from images WHERE id = ?";
		return getJdbcTemplate().queryForLong(sql, imageId);
	}
	
	@Override
	public long checkVideoPlaceOwner(long videoId) {
		String sql = "SELECT Place from videos WHERE id = ?";
		return getJdbcTemplate().queryForLong(sql, videoId);
	}
	
	@Override
	public int checkNewsPlaceManager(long newsId,long uid) {
		String sql = "SELECT count(*) from managers m INNER JOIN (SELECT Place from news WHERE id = ?) news ON m.Place = news.Place WHERE User = ?";
		return getJdbcTemplate().queryForInt(sql, newsId);
	}
	
	@Override
	public int checkPlaceManager(long placeId, long uid) {
		String sql = "SELECT count(*) from managers Where Place = ? AND User = ?";
		return getJdbcTemplate().queryForInt(sql, placeId, uid);
	}

	@Override
	public int checkImgPlaceManager(long imageId,long userId) {
		String sql = "SELECT count(*) from managers m INNER JOIN (SELECT Place from images WHERE id = ?) img ON m.Place = img.Place WHERE User = ?";
		return getJdbcTemplate().queryForInt(sql, imageId, userId);
	}

	@Override
	public int checkVideoPlaceManager(long videoId,long userId) {
		String sql = "SELECT count(*) from managers m INNER JOIN (SELECT Place from videos WHERE id = ?) vid ON m.Place = vid.Place WHERE User = ?";
		return getJdbcTemplate().queryForInt(sql, videoId , userId);
	}
	
}
