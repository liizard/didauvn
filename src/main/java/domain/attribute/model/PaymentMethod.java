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
package domain.attribute.model;

public class PaymentMethod {

	private long placeId = 0;
	private boolean payByCash = true;
	private boolean payByVisa = false;
	private boolean payByMasterCard = false;

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public boolean isPayByCash() {
		return payByCash;
	}

	public void setPayByCash(boolean payByCash) {
		this.payByCash = payByCash;
	}

	public boolean isPayByVisa() {
		return payByVisa;
	}

	public void setPayByVisa(boolean payByVisa) {
		this.payByVisa = payByVisa;
	}

	public boolean isPayByMasterCard() {
		return payByMasterCard;
	}

	public void setPayByMasterCard(boolean payByMasterCard) {
		this.payByMasterCard = payByMasterCard;
	}

}
