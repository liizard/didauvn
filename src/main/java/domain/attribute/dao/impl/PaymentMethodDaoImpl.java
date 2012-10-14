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

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.PaymentMethodDao;
import domain.attribute.model.PaymentMethod;


@Repository("paymentMethodDao")
public class PaymentMethodDaoImpl extends JdbcDaoSupport implements
		PaymentMethodDao {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init() {
		setDataSource(dataSource);
	}

	@Override
	public void updatePaymentMethod(PaymentMethod paymentMethod) {
		String sql = "UPDATE PaymentMethods SET PaymentCash=?, PaymentVisa=?, PaymentMasterCard=? "
				+ "WHERE Place=?";
		getJdbcTemplate().update(
				sql,
				new Object[] { paymentMethod.isPayByCash(),
						paymentMethod.isPayByVisa(),
						paymentMethod.isPayByMasterCard(),
						paymentMethod.getPlaceId() });
	}

	@Override
	public PaymentMethod getPaymentMethods(long placeId) {
		String sql = "SELECT * FROM PaymentMethods WHERE place=?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				new Object[] { placeId });
		return rows.isEmpty() ? null : rowMapperPaymentMethod(rows.get(0));
	}

	private PaymentMethod rowMapperPaymentMethod(Map<String, Object> row) {
		PaymentMethod paymentMethod = new PaymentMethod();

		paymentMethod.setPlaceId((Long) row.get("place"));
		paymentMethod.setPayByCash((Boolean) row.get("PaymentCash"));
		paymentMethod.setPayByVisa((Boolean) row.get("PaymentVisa"));
		paymentMethod
				.setPayByMasterCard((Boolean) row.get("PaymentMasterCard"));

		return paymentMethod;
	}

}
