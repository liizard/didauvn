package admin.model.req;

import java.util.Date;

public class BanRq {
	private long id;
	private String user;
	private Date banDate;
	private long noDay;
	private String reason;
	private String admin;

	public BanRq() {
		id = 0;
		user = null;
		banDate = null;
		noDay = 0;
		reason = null;
		admin = null;
	}

	public BanRq(long id, String user, Date banDate, long noDay, String reason,
			String admin) {
		super();
		this.id = id;
		this.user = user;
		this.banDate = banDate;
		this.noDay = noDay;
		this.reason = reason;
		this.admin = admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getBanDate() {
		return banDate;
	}

	public void setBanDate(Date banDate) {
		this.banDate = banDate;
	}

	public long getNoDay() {
		return noDay;
	}

	public void setNoDay(long noDay) {
		this.noDay = noDay;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
