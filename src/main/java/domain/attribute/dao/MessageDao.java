package domain.attribute.dao;

import java.util.List;

import domain.attribute.model.MessageGeneral;

public interface MessageDao {
	void insert(long placeId, long userId, int messageType);

	List<MessageGeneral> getMoreMessages(String criteria, long userId,
			long currentMessage, long from, int count);

	long getUnreadMessages(long userId);

	void updateCount(long placeId, long userId);

	List<MessageGeneral> getMessages(String criteria, long userId, long from,
			int count);

	void deleteCount(long userId);
}
