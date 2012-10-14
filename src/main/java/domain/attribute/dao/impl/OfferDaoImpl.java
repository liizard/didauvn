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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.OfferDao;
import domain.attribute.model.OfferWeek;

@Repository("offerDao")
public class OfferDaoImpl extends JdbcDaoSupport implements OfferDao {
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public List<OfferWeek> getOffer(long categoryId, int from, int count) {
		String sql = "SELECT Places.Id as PlaceId, Places.Name as PlaceName, Tags.Name as TagName, "
				+ "Offers.Id as OfferId, Title, Offers.Dcrp as Dcrp, CreateDate, Last, Discount, Percent "
				+ "FROM Places INNER JOIN Tags ON Places.Tag = Tags.Id "
				+ "INNER JOIN Offers ON Offers.Place = Places.Id "
				+ "WHERE Places.BusinessType = ? ORDER BY CreateDate DESC LIMIT ?,?";
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql,
				categoryId, from, count);
		List<OfferWeek> list = new ArrayList<OfferWeek>();
		for (Map<String, Object> row : result) {
			list.add(rowMapper(row));
		}
		return list;
	}
	
	
	private OfferWeek rowMapper(Map<String, Object> row) {
		OfferWeek offerWeek = new OfferWeek();
		offerWeek.setId((Long) row.get("OfferId"));
		offerWeek.setPlaceId((Long) row.get("PlaceId"));
		offerWeek.setPlaceName((String) row.get("PlaceName"));
		offerWeek.setTagName((String) row.get("TagName"));
		offerWeek.setTitle((String) row.get("Title"));
		offerWeek.setContent((String) row.get("Dcrp"));
		offerWeek.setDate((Date) row.get("CreateDate"));
		offerWeek.setLast((Integer) row.get("Last"));
		offerWeek.setDiscount((Boolean) row.get("Discount"));
		offerWeek.setPercent((Integer) row.get("Percent"));
		return offerWeek;
	}

}
