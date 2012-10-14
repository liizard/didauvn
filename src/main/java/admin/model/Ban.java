package admin.model;

import java.util.Date;

public class Ban {
	
	public static final String USER_NOT_EXIST_CODE = "err280";
	public static final String USER_NOT_EXIST = "User is not existed";
	public static final String NO_DAY_INVALID_CODE = "err281";
	public static final String NO_DAY_INVALID = "Number of Day is Invalid";
	public static final String BAN_DATE_INVALID_CODE = "err282";
	public static final String BAN_DATE_INVALID = "Ban Date is Invalid";
	public static final String USER_HAS_BANNED_CODE = "err283";
	public static final String USER_HAS_BANNED = "User has been banned";
	
	private long id;
	private long userId;
	private Date banDate;
	private long noDay;
	private String reason;
	private long adminId;

	public Ban() {
	}

	public Ban(long userId, Date banDate, long noDay, String reason, long adminId) {
		super();
		this.userId = userId;
		this.banDate = banDate;
		this.noDay = noDay;
		this.reason = reason;
		this.adminId = adminId;
	}

	public Ban(long id, Date banDate, long noDay, String reason) {
		super();
		this.id = id;
		this.banDate = banDate;
		this.noDay = noDay;
		this.reason = reason;
	}

	public Ban(long id, long userId, Date banDate, long noDay, String reason,
			long adminId) {
		super();
		this.id = id;
		this.userId = userId;
		this.banDate = banDate;
		this.noDay = noDay;
		this.reason = reason;
		this.adminId = adminId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

}
