package domain.user.service;

import java.util.List;

import domain.attribute.model.MessageGeneral;

public interface MessageService {
	List<MessageGeneral> getMoreMessages(long currentMessage);

	long getUnreadMessages();

	List<MessageGeneral> getMessages();

	void updateCount(long count);

	void deleteCount();
}
