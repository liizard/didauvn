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

import java.util.ArrayList;
import java.util.List;

public class TagUpdate {

	private List<PlaceTag> add;
	private List<PlaceTag> remove;
	private PlaceTag mainTag;
	
	public TagUpdate() {
		add = new ArrayList<PlaceTag>();
		remove = new ArrayList<PlaceTag>();
		mainTag = new PlaceTag();
	}

	public List<PlaceTag> getAdd() {
		return add;
	}

	public void setAdd(List<PlaceTag> add) {
		this.add = add;
	}

	public List<PlaceTag> getRemove() {
		return remove;
	}

	public void setRemove(List<PlaceTag> remove) {
		this.remove = remove;
	}

	public PlaceTag getMainTag() {
		return mainTag;
	}

	public void setMainTag(PlaceTag mainTag) {
		this.mainTag = mainTag;
	}
}
