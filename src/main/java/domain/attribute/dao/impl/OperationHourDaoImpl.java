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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.OperationHourDao;
import domain.attribute.model.OperationHour;

@Repository("operationHourDao")
public class OperationHourDaoImpl extends JdbcDaoSupport implements
		OperationHourDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public List<OperationHour> getOperationHours(long placeId) {
		String sql = "SELECT Place, Day, OpenHour, CloseHour, Remark, Active "
				+ " FROM OperationHours WHERE Place = ? ORDER BY Day";
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql,
				placeId);

		List<OperationHour> operationHours = new ArrayList<OperationHour>();
		for (Map<String, Object> m : result) {
			operationHours.add(rowMapper(m));
		}
		return operationHours;
	}

	@Override
	public void insert(long placeId) {
		String[] sqls = new String[7];
		for (int i = 0; i < 7; i++) {
			sqls[i] = "INSERT INTO OperationHours (Place, Day, OpenHour, CloseHour, Remark, Active) "
					+ "VALUES("
					+ placeId
					+ ", "
					+ (i + 1)
					+ ", 18, 34, '', true)";
		}
		getJdbcTemplate().batchUpdate(sqls);
	}

	@Override
	public void update(final long placeId,
			final List<OperationHour> operationHours) {
		String sql = "UPDATE OperationHours SET OpenHour=?, CloseHour=?, Remark=?, Active=?"
				+ " WHERE Place=? AND Day=?";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				OperationHour opHour = operationHours.get(i);
				ps.setInt(1, opHour.getOpenHour());
				ps.setInt(2, opHour.getCloseHour());
				ps.setString(3, opHour.getRemark());
				ps.setBoolean(4, opHour.isActive());
				ps.setLong(5, placeId);
				ps.setInt(6, opHour.getDay());
			}

			@Override
			public int getBatchSize() {
				return operationHours.size();
			}
		});
	}

	private OperationHour rowMapper(Map<String, Object> row) {
		OperationHour operationHour = new OperationHour();

		operationHour.setDay((Integer) row.get("day"));
		operationHour.setOpenHour((Integer) row.get("OpenHour"));
		operationHour.setCloseHour((Integer) row.get("CloseHour"));
		operationHour.setRemark((String) row.get("Remark"));
		operationHour.setActive((Boolean) row.get("active"));

		return operationHour;
	}

}
