package domain.user.model;

public class UserFB {
	private long uid;
	private String name;
	private int status;
	private String imgUrl;
	private String profileUrl;

	public UserFB() {
		this.uid = 0;
		this.name = "";
		this.status = 0;
		this.imgUrl = "";
		this.profileUrl = "";
	}

	public UserFB(long uid, String name, int status, String imgUrl,
			String profileUrl) {
		super();
		this.uid = uid;
		this.name = name;
		this.status = status;
		this.imgUrl = imgUrl;
		this.profileUrl = profileUrl;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

}
