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

import core.constant.SystemConstant;
import core.constant.WebConstant;

import admin.dao.ReportDao;
import admin.model.PageBuilder;
import admin.model.ReportItem;
import admin.model.ReportTypeEnum;
import admin.model.req.ReportFilterRq;
import admin.model.req.ReportRq;

@Repository("reportDao")
public class ReportDaoImpl extends JdbcDaoSupport implements ReportDao {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	private ReportItem rowMapper(Map<String, Object> row) {
		// get report type
		ReportTypeEnum type = ReportTypeEnum.values()[(Integer) row
				.get("ReportType")];

		// get value for report item
		ReportItem item = new ReportItem();
		item.setId((Long) row.get("Id"));
		item.setItemId((Long) row.get("itemId"));
		item.setUser((Long) row.get("user"));
		item.setReportType(type.name());
		item.setDcrp((String) row.get("dcrp"));
		item.setCreateDate((Date) row.get("createDate"));
		item.setProcessed((Boolean) row.get("processed"));

		// set link to Report
		switch (type) {
		case PLACE:
			item.setLinkTo(SystemConstant.DOMAIN + ""
					+ ReportItem.PLACE_LINK_TO + "" + item.getItemId());
			break;
		case IMAGE:
			item.setLinkTo(SystemConstant.DOMAIN + ""
					+ ReportItem.IMAGE_LINK_TO + "" + item.getItemId());
			break;
		case VIDEO:
			item.setLinkTo(SystemConstant.DOMAIN + ""
					+ ReportItem.VIDEO_LINK_TO + "" + item.getItemId());
			break;
		default:
			break;
		}

		return item;
	}

	@Override
	public long getPageCount() {
		String sql = "SELECT COUNT(Id) AS count FROM Reports";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		long count = (Long) rows.get(0).get("count");
		return count;
	}

	private long countByFilter(String filter) {
		String sql = "SELECT COUNT(Id) AS count FROM Reports " + filter;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		long count = (Long) rows.get(0).get("count");
		long pageNum = (long) Math.ceil((double) count
				/ WebConstant.ADMIN_REPORT_PER_PAGE);
		return pageNum;
	}

	@Override
	public List<ReportItem> getByPage(int page, int count) {
		String sql = "SELECT * FROM Reports ORDER BY Id DESC LIMIT ?,?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				page, count);
		List<ReportItem> items = new ArrayList<ReportItem>();
		for (Map<String, Object> row : rows) {
			items.add(rowMapper(row));
		}
		return items;
	}

	public void batchDelete(int[] reportItemIdArr) {
		if (reportItemIdArr != null && reportItemIdArr.length > 0) {
			String[] sqls = new String[reportItemIdArr.length];
			for (int i = 0; i < reportItemIdArr.length; i++) {
				sqls[i] = "DELETE FROM Reports WHERE id = "
						+ reportItemIdArr[i];
			}
			getJdbcTemplate().batchUpdate(sqls);
		}
	}

	public void batchProcess(int[] reportItemIdArr) {
		if (reportItemIdArr != null && reportItemIdArr.length > 0) {
			String[] sqls = new String[reportItemIdArr.length];
			for (int i = 0; i < reportItemIdArr.length; i++) {
				sqls[i] = "UPDATE Reports SET processed = true WHERE id = "
						+ reportItemIdArr[i];
			}
			getJdbcTemplate().batchUpdate(sqls);
		}
	}

	public void process(int reportId) {
		String sql = "UPDATE Reports SET processed = true WHERE id = ?";
		getJdbcTemplate().update(sql, reportId);
	}

	public void delete(int reportId) {
		String sql = "DELETE FROM Reports WHERE id = ?";
		getJdbcTemplate().update(sql, reportId);
	}

	@Override
	public List<ReportItem> getPendings() {
		String sql = "SELECT * FROM Reports WHERE processed = false";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<ReportItem> items = new ArrayList<ReportItem>();
		for (Map<String, Object> row : rows) {
			items.add(rowMapper(row));
		}
		return items;
	}

	@Override
	public List<ReportItem> getResolveds() {
		String sql = "SELECT * FROM Reports WHERE processed = true";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		List<ReportItem> items = new ArrayList<ReportItem>();
		for (Map<String, Object> row : rows) {
			items.add(rowMapper(row));
		}
		return items;
	}

	@Override
	public PageBuilder<ReportItem> getByFilter(ReportFilterRq filterRq) {
		String filter = convertFilter(filterRq);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Reports ");
		sql.append(filter);
		sql.append(" ORDER BY Id DESC LIMIT "
				+ (filterRq.getPage() * WebConstant.ADMIN_REPORT_PER_PAGE)
				+ ", " + WebConstant.ADMIN_REPORT_PER_PAGE + " ");
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				sql.toString());
		List<ReportItem> items = new ArrayList<ReportItem>();
		for (Map<String, Object> row : rows) {
			items.add(rowMapper(row));
		}
		long count = countByFilter(filter);
		return new PageBuilder<ReportItem>(items, count, 0);
	}

	@Override
	public long insert(final ReportRq reportRq) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO Reports(ReportType, ItemId, User, Dcrp, CreateDate, Processed)"
								+ "VALUES(?,?,?,?,NOW(),?)",
						Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, (ReportTypeEnum.valueOf(reportRq
						.getReportType().toUpperCase())).ordinal());
				pstmt.setLong(2, reportRq.getItemId());
				pstmt.setLong(3, reportRq.getUser());
				pstmt.setString(4, reportRq.getDcrp());
				pstmt.setBoolean(5, reportRq.isProcessed());
				return pstmt;
			}
		};
		getJdbcTemplate().update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	private String convertFilter(ReportFilterRq reportRq) {
		StringBuilder sql = new StringBuilder();
		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		sql.append(" WHERE ");
		if (reportRq.getFrom() != null && !reportRq.getFrom().isEmpty()) {
			try {
				sql.append(" CreateDate >= '"
						+ myFormat.format(fromUser.parse(reportRq.getFrom()))
						+ "' ");
			} catch (ParseException e) {
				sql.append(" CreateDate >= ' ' ");
			}
		} else {
			sql.append(" CreateDate >= ' ' ");
		}
		if (!reportRq.getTo().isEmpty() && reportRq.getTo() != null) {
			try {
				sql.append(" AND CreateDate <= '"
						+ myFormat.format(fromUser.parse(reportRq.getTo()))
						+ "' ");
			} catch (ParseException e) {
				sql.append(" AND CreateDate <= ' ' ");
			}
		}

		if (reportRq.getReportTypeValue() != -1) {
			sql.append(" AND ReportType = " + reportRq.getReportTypeValue()
					+ " ");
		}

		if (reportRq.isProcessedItemSelected()
				^ reportRq.isUnProcessedItemSelected()) {
			if (reportRq.isProcessedItemSelected()) {
				sql.append(" AND Processed = true ");
			} else if (reportRq.isUnProcessedItemSelected()) {
				sql.append(" AND Processed = false ");
			}
		}

		// System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public boolean isDuplicate(ReportRq reportRq) {
		String sql = "SELECT ItemId, User FROM Reports WHERE ItemId = ? AND User = ? "
				+ " AND HOUR(TIMEDIFF(Now(), CreateDate)) < 24 ";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				reportRq.getItemId(), reportRq.getUser());
		if (rows.isEmpty()) {
			return false;
		}
		return true;
	}
}
