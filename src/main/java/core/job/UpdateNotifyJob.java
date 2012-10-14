package core.job;

import domain.attribute.dao.MessageDao;

public class UpdateNotifyJob implements Job{
	
	private MessageDao messageDao;
	
	private long placeId;
	private long userId;
	private int messageType;
	
	
	public UpdateNotifyJob() {
		super();
	}

	public UpdateNotifyJob(long placeId, long userId, int messageType) {
		super();
		this.placeId = placeId;
		this.userId = userId;
		this.messageType = messageType;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		messageDao.insert(placeId, userId, messageType);
		messageDao.updateCount(placeId, userId);
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
}
