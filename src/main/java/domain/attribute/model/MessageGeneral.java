package domain.attribute.model;


public class MessageGeneral {
	private long messageId;
	private long userId;
	private String username;
	private String placename;
	private long placeId;
	private int type;
	private String createDate;
	private String avatar;
	public MessageGeneral(long messageId,long userId, String username, String placename,
			long placeId,int type) {
		super();
		this.setMessageId(messageId);
		this.userId = userId;
		this.username = username;
		this.placename = placename;
		this.placeId = placeId;
		this.setType(type);
	}
	public MessageGeneral(){
		
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
