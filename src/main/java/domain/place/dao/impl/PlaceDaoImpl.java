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

import domain.place.dao.PlaceDao;
import domain.place.model.PlaceDetail;
import domain.place.model.PlaceGeneral;
import domain.place.model.rq.PlaceRq;

@Repository("placeDao")
public class PlaceDaoImpl extends JdbcDaoSupport implements PlaceDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public long insertPlace(final PlaceRq place, final long userId) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con
						.prepareStatement(
								"INSERT INTO Places(Name, Dcrp, Address, Email, Website, "
										+ "Phone, Fax, BusinessType, Street, Owner, LocationX, LocationY)"
										+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, place.getName());
				pstmt.setString(2, place.getDcrp());
				pstmt.setString(3, place.getAddress());
				pstmt.setString(4, place.getEmail());
				pstmt.setString(5, place.getWebsite());
				pstmt.setString(6, place.getPhone());
				pstmt.setString(7, place.getFax());
				pstmt.setInt(8, place.getBusinessTypeId());
				pstmt.setLong(9, place.getStreetId());
				pstmt.setLong(10, userId);
				pstmt.setString(11, place.getLocationX());
				pstmt.setString(12, place.getLocationY());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updatePlace(PlaceRq place) {
		String sql = "UPDATE Places SET Name=?, Dcrp=?, Address=?, Email=?, Website=?, "
				+ "Phone=?, Fax=?, BusinessType=?, Tag=?, Street=?, LocationX=?, LocationY=? WHERE Id=?";
		getJdbcTemplate().update(sql, place.getName(), place.getDcrp(),
				place.getAddress(), place.getEmail(), place.getWebsite(),
				place.getPhone(), place.getFax(), place.getBusinessTypeId(),
				place.getTagId(), place.getStreetId(), place.getLocationX(),
				place.getLocationY(), place.getId());
	}

	@Override
	public PlaceRq getPlace(long id) {
		String sql = "SELECT Id, Name, Dcrp, Address, Email, Website, Phone, Fax, LocationX, LocationY,"
				+ "BusinessTypeId, TagId, StreetId, WardId, DistrictId, CityId "
				+ "FROM PlaceView WHERE Id=?";
		Map<String, Object> row = getJdbcTemplate().queryForMap(sql, id);

		// TagId may be null
		long tagId = 0;
		try {
			tagId = (Long) row.get("TagId");
		} catch (Exception e) {
		}
		return new PlaceRq(id, (String) row.get("Name"),
				(String) row.get("Dcrp"), (String) row.get("Address"),
				(String) row.get("Website"), (String) row.get("Phone"),
				(String) row.get("Fax"), (String) row.get("Email"),
				(String) row.get("LocationX"), (String) row.get("LocationY"),
				(Integer) row.get("BusinessTypeId"), tagId,
				(Long) row.get("StreetId"), (Long) row.get("WardId"),
				(Integer) row.get("DistrictId"), (Integer) row.get("CityId"));
	}

	@Override
	public PlaceDetail getPlaceDetail(long id, String locale) {
		String sql = "SELECT Id, Name, Dcrp, Address, Website, Phone, Fax, Email, Avatar, LocationX, LocationY, "
				+ "TagName, BusinessTypeName_"
				+ locale
				+ " AS BusinessTypeName,"
				+ "StreetName_"
				+ locale
				+ " AS StreetName,"
				+ "WardName_"
				+ locale
				+ " AS WardName,DistrictName_"
				+ locale
				+ " AS DistrictName,CityName_"
				+ locale
				+ " AS CityName "
				+ "FROM PlaceView WHERE Id=?";
		Map<String, Object> row = getJdbcTemplate().queryForMap(sql, id);
		return new PlaceDetail(id, (Long) row.get("Avatar"),
				(String) row.get("Name"), (String) row.get("Dcrp"),
				(String) row.get("Address"), (String) row.get("Website"),
				(String) row.get("Phone"), (String) row.get("Fax"),
				(String) row.get("Email"), (String) row.get("TagName"),
				(String) row.get("BusinessTypeName"),
				(String) row.get("StreetName"), (String) row.get("WardName"),
				(String) row.get("DistrictName"), (String) row.get("CityName"),
				(String) row.get("LocationX"), (String) row.get("LocationY"));
	}

	@Override
	public List<PlaceGeneral> searchPlace(String locale, String tagIds,
			String locationQuery, int from, int count) {
		String sql = "SELECT Id,Name,Phone,Email,Website,Address,Avatar,TagName,"
				+ "StreetName_"
				+ locale
				+ " AS StreetName,"
				+ "WardName_"
				+ locale
				+ " AS WardName,"
				+ "DistrictName_"
				+ locale
				+ " AS DistrictName,"
				+ "CityName_"
				+ locale
				+ " AS CityName, LocationX, LocationY, BusinessTypeId "
				+ "FROM PlaceView WHERE " + locationQuery;
		if (tagIds.length() > 0) {
			sql += " AND Id IN (Select Place FROM PlaceTags WHERE Tag IN("
					+ tagIds + "))";
		}
		sql += " ORDER BY Id DESC  LIMIT ?,?";

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				from, count);
		List<PlaceGeneral> placeGenerals = new ArrayList<PlaceGeneral>();
		for (Map<String, Object> row : rows) {
			placeGenerals.add(rowMapperPlaceGeneral(row));
		}
		return placeGenerals;
	}

	public List<PlaceGeneral> getPlaceOwner(String locale, long userId) {
		String sql = "SELECT Id,Name,Phone,Email,Website,Address,Avatar,TagName,"
				+ "StreetName_"
				+ locale
				+ " AS StreetName,"
				+ "WardName_"
				+ locale
				+ " AS WardName,"
				+ "DistrictName_"
				+ locale
				+ " AS DistrictName,"
				+ "CityName_"
				+ locale
				+ " AS CityName, LocationX, LocationY, BusinessTypeId "
				+ "FROM PlaceView WHERE Owner=? ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				userId);
		List<PlaceGeneral> placeGenerals = new ArrayList<PlaceGeneral>();
		for (Map<String, Object> row : rows) {
			placeGenerals.add(rowMapperPlaceGeneral(row));
		}
		return placeGenerals;
	}

	public List<PlaceGeneral> getPlaceManager(String locale, String criteria) {
		String sql = "SELECT Id,Name,Phone,Email,Website,Address, Avatar, TagName,"
				+ "StreetName_"
				+ locale
				+ " AS StreetName,"
				+ "WardName_"
				+ locale
				+ " AS WardName,"
				+ "DistrictName_"
				+ locale
				+ " AS DistrictName,"
				+ "CityName_"
				+ locale
				+ " AS CityName, LocationX, LocationY, BusinessTypeId "
				+ "FROM PlaceView WHERE Id IN ("+criteria+") ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<PlaceGeneral> placeGenerals = new ArrayList<PlaceGeneral>();
		for (Map<String, Object> row : rows) {
			placeGenerals.add(rowMapperPlaceGeneral(row));
		}
		return placeGenerals;
	}

	public void updateAvatar(long placeId, long imageId) {
		String sql = "UPDATE Places SET Avatar=? WHERE Id=?";
		getJdbcTemplate().update(sql, imageId, placeId);
	}

	private PlaceGeneral rowMapperPlaceGeneral(Map<String, Object> row) {
		return new PlaceGeneral((Long) row.get("Id"), (Long) row.get("Avatar"),
				(String) row.get("Name"), (String) row.get("Phone"),
				(String) row.get("Email"), (String) row.get("Website"),
				(String) row.get("Address"), (String) row.get("TagName"),
				(String) row.get("StreetName"), (String) row.get("WardName"),
				(String) row.get("DistrictName"), (String) row.get("CityName"),
				(String) row.get("LocationX"), (String) row.get("LocationY"),
				(Integer) row.get("BusinessTypeId"));
	}

	@Override
	public List<PlaceGeneral> getPlaceWow(String locale,String criteria) {
		String sql = "SELECT Id,Name,Phone,Email,Website,Address, Avatar, TagName,"
				+ "StreetName_"
				+ locale
				+ " AS StreetName,"
				+ "WardName_"
				+ locale
				+ " AS WardName,"
				+ "DistrictName_"
				+ locale
				+ " AS DistrictName,"
				+ "CityName_"
				+ locale
				+ " AS CityName, LocationX, LocationY, BusinessTypeId "
				+ "FROM PlaceView WHERE Id IN ("+criteria+") ORDER BY Id DESC";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<PlaceGeneral> placeGenerals = new ArrayList<PlaceGeneral>();
		for (Map<String, Object> row : rows) {
			placeGenerals.add(rowMapperPlaceGeneral(row));
		}
		return placeGenerals;
	}

	@Override
	public void updateViewCount(long placeId) {
		String sql = "UPDATE placeranks SET ViewCount = ViewCount + 1 WHERE Place = ?";
		getJdbcTemplate().update(sql,placeId);
	}

	@Override
	public int getWowCount(long placeId) {
		String sql = "SELECT SubscriberCount FROM placeranks WHERE place = ?";
		return getJdbcTemplate().queryForInt(sql, placeId);
	}
	
}
