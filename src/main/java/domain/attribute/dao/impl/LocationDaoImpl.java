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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.LocationDao;
import domain.attribute.model.District;
import domain.attribute.model.Street;
import domain.attribute.model.Ward;
import domain.place.model.LocationSearch;

@Repository("locationDao")
public class LocationDaoImpl extends JdbcDaoSupport implements LocationDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public List<District> getDistricts(String locale, long cityId) {
		String sql = "SELECT Id, Name_" + locale
				+ " AS Name, City FROM Districts WHERE City=?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				cityId);
		List<District> districts = new ArrayList<District>();
		District district;
		for (Map<String, Object> row : rows) {
			district = new District((Integer) row.get("Id"),
					(String) row.get("Name"), (Integer) row.get("City"));
			districts.add(district);
		}
		return districts;
	}

	@Override
	public List<Ward> getWards(String locale, long districtId) {
		String sql = "SELECT w.Id, wn.Name_"
				+ locale
				+ " AS Name, w.District FROM Wards w "
				+ "INNER JOIN WardNames wn ON w.WardName=wn.Id WHERE w.District=? ORDER BY wn.Name_"
				+ locale;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				districtId);
		List<Ward> wards = new ArrayList<Ward>();
		Ward ward;
		for (Map<String, Object> row : rows) {
			ward = new Ward((Long) row.get("Id"), (String) row.get("Name"),
					(Integer) row.get("District"));
			wards.add(ward);
		}
		return wards;
	}

	@Override
	public List<Street> getStreets(String locale, long wardId) {
		String sql = "SELECT s.Id, sn.Name_"
				+ locale
				+ " AS Name, s.Ward FROM Streets s "
				+ "INNER JOIN StreetNames sn ON s.StreetName=sn.Id WHERE s.Ward=? ORDER BY sn.Name_"
				+ locale;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				wardId);
		List<Street> streets = new ArrayList<Street>();
		Street street;
		for (Map<String, Object> row : rows) {
			street = new Street((Long) row.get("Id"), (String) row.get("Name"),
					(Long) row.get("Ward"));
			streets.add(street);
		}
		return streets;
	}

	@Override
	public List<LocationSearch> searchLocation(String locale, String location) {
		// Search street name
		String sql = "(SELECT StreetId, 0 AS WardId, 0 AS DistrictId, StreetName_"
				+ locale
				+ " AS StreetName, WardName_"
				+ locale
				+ " AS WardName, DistrictName_"
				+ locale
				+ " AS DistrictName FROM LocationView WHERE StreetName_"
				+ locale
				+ " LIKE '"
				+ location
				+ "%' LIMIT 0,10) "
				// Search ward name
				+ "UNION (SELECT 0 AS StreetId, WardId, 0 AS DistrictId, '' AS StreetName, WardName_"
				+ locale
				+ " AS WardName, DistrictName_"
				+ locale
				+ " AS DistrictName FROM WardView WHERE WardName_"
				+ locale
				+ " LIKE '"
				+ location
				+ "%' LIMIT 0,10) "
				// Search district name
				+ "UNION (SELECT 0 AS StreetId, 0 AS WardId, Id, '' AS StreetName, '' AS WardName, "
				+ "Name_"
				+ locale
				+ " AS DistrictName FROM Districts WHERE Name_"
				+ locale
				+ " LIKE '" + location + "%' LIMIT 0,10) ";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<LocationSearch> locationSearchResults = new ArrayList<LocationSearch>();
		for (Map<String, Object> row : rows) {
			locationSearchResults.add(rowMapperLocationSearchResult(row));
		}
		return locationSearchResults;
	}

	private LocationSearch rowMapperLocationSearchResult(Map<String, Object> row) {
		String display = "";
		display += (String) row.get("StreetName");
		display += " " + (String) row.get("WardName");
		display += " " + (String) row.get("DistrictName");
		return new LocationSearch((Long) row.get("DistrictId"),
				(Long) row.get("WardId"),
				((BigInteger) row.get("StreetId")).longValue(), display);
	}
}
