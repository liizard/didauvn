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

import java.util.List;

import domain.place.model.PlaceGeneral;

public class NewPlace {
	private PlaceGeneral placeView;
	private List<Integer> imgs;

	public NewPlace() {

	}

	public PlaceGeneral getPlaceView() {
		return placeView;
	}

	public void setPlaceView(PlaceGeneral placeView) {
		this.placeView = placeView;
	}

	public List<Integer> getImgs() {
		return imgs;
	}

	public void setImgs(List<Integer> imgs) {
		this.imgs = imgs;
	}

}
