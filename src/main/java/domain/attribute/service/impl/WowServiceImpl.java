package domain.attribute.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.context.ContextHolder;
import core.job.executor.AsyncJobExecutor;
import domain.attribute.dao.MessageDao;
import domain.attribute.dao.WowDao;
import domain.attribute.service.WowService;

@Service
public class WowServiceImpl implements WowService {

	@Autowired
	private WowDao wowDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public void insert(long placeid) {
		wowDao.insert(placeid, ContextHolder.getInstance().getCurrentUserId());
		ContextHolder.getInstance().getUserPlaceWow().add(placeid);

		// update message and count after function
//		UpdateNotifyJob job = new UpdateNotifyJob(placeid, ContextHolder
//				.getInstance().getCurrentUserId(), MessageEnum.WOW.ordinal());
//		job.setMessageDao(messageDao);
//		asyncJobExecutor.execute(job);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER')")
	public void delete(long placeid) {
		wowDao.delete(placeid, ContextHolder.getInstance().getCurrentUserId());
		ContextHolder.getInstance().getUserPlaceWow().remove(placeid);
	}

	@Override
	public List<Long> gets(long placeid) {
		return wowDao.getUsersWow(placeid);
	}
}
