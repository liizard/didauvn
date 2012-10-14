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
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.BusinessTypeDao;
import domain.attribute.model.BusinessType;


@Repository("businessTypeDao")
public class BusinessTypeDaoImpl extends JdbcDaoSupport implements
		BusinessTypeDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public List<BusinessType> getBusinessTypes(String locale) {
		String sql = "SELECT Id, Name_" + locale
				+ " AS Name FROM BusinessTypes";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<BusinessType> businessGroups = new ArrayList<BusinessType>();
		BusinessType businessGroup;
		for (Map<String, Object> row : rows) {
			businessGroup = new BusinessType((Integer) row.get("Id"),
					(String) row.get("Name"));
			businessGroups.add(businessGroup);
		}
		return businessGroups;
	}
}
