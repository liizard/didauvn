package domain.user.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.constant.WebConstant;
import core.context.ContextHolder;
import domain.attribute.dao.MessageDao;
import domain.attribute.model.MessageGeneral;
import domain.user.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<MessageGeneral> getMoreMessages(long currentMessage) {
		Set<Long> placeids = new HashSet<Long>(ContextHolder.getInstance()
				.getUserPlaceWow());
		placeids.addAll(ContextHolder.getInstance().getUserPlaceOwner());
		placeids.addAll(ContextHolder.getInstance().getUserPlaceManager());
		if (!placeids.isEmpty()) {
			StringBuilder criteria = new StringBuilder("(");
			Iterator<Long> i = placeids.iterator();
			if (i.hasNext()) {
				criteria.append(i.next());
			}
			while (i.hasNext()) {
				criteria.append(",").append(i.next());
			}
			criteria.append(")");
			return messageDao.getMoreMessages(criteria.toString(),
					ContextHolder.getInstance().getCurrentUserId(),
					currentMessage, 0, WebConstant.MESSAGE_PER_PAGE);
		} else {
			return null;
		}

	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public void updateCount(long count) {
		messageDao.updateCount(ContextHolder.getInstance().getCurrentUserId(),
				count);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public long getUnreadMessages() {
		return messageDao.getUnreadMessages(ContextHolder.getInstance()
				.getCurrentUserId());
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<MessageGeneral> getMessages() {
		Set<Long> placeids = new HashSet<Long>(ContextHolder.getInstance()
				.getUserPlaceWow());
		placeids.addAll(ContextHolder.getInstance().getUserPlaceOwner());
		placeids.addAll(ContextHolder.getInstance().getUserPlaceManager());
		if (!placeids.isEmpty()) {
			StringBuilder criteria = new StringBuilder("(");
			Iterator<Long> i = placeids.iterator();
			if (i.hasNext()) {
				criteria.append(i.next());
			}
			while (i.hasNext()) {
				criteria.append(",").append(i.next());
			}
			criteria.append(")");
			return messageDao.getMessages(criteria.toString(), ContextHolder
					.getInstance().getCurrentUserId(), 0,
					WebConstant.MESSAGE_PER_PAGE);
		} else {
			return null;
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public void deleteCount() {
		messageDao.deleteCount(ContextHolder.getInstance().getCurrentUserId());
	}

}
