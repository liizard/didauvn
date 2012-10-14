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
package domain.user.model;

import java.util.List;

public class UserSession {
	private User user;
	private List<Long> ownerPlaces;
	private List<Long> managerPlaces;
	private List<Long> wowPlaces;
	private boolean isAdmin;

	public UserSession() {
		user = new User();
		isAdmin = false;
	}

	public UserSession(User user, List<Long> ownerPlaces,
			List<Long> managerPlaces, List<Long> wowPlaces, boolean isAdmin) {
		super();
		this.user = user;
		this.ownerPlaces = ownerPlaces;
		this.managerPlaces = managerPlaces;
		this.wowPlaces = wowPlaces;
		this.isAdmin = isAdmin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Long> getOwnerPlaces() {
		return ownerPlaces;
	}

	public void setOwnerPlaces(List<Long> ownerPlaces) {
		this.ownerPlaces = ownerPlaces;
	}

	public List<Long> getManagerPlaces() {
		return managerPlaces;
	}

	public void setManagerPlaces(List<Long> managerPlaces) {
		this.managerPlaces = managerPlaces;
	}

	public List<Long> getWowPlaces() {
		return wowPlaces;
	}

	public void setWowPlaces(List<Long> wowPlaces) {
		this.wowPlaces = wowPlaces;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
