package admin.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.dao.BanDao;
import admin.model.Ban;
import admin.model.req.BanRq;
import admin.service.BanService;
import core.constant.WebConstant;
import core.context.ContextHolder;
import core.exception.DdException;
import domain.user.dao.UserDao;
import domain.user.model.User;

@Service
public class BanServiceImpl implements BanService {
	@Autowired
	private BanDao banDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<BanRq> getBans() {
		List<Ban> bans = banDao.getBans();
		List<BanRq> banRqs = new ArrayList<BanRq>();
		for (Ban ban : bans) {
			banRqs.add(new BanRq(ban.getId(), userDao.findById(ban.getUserId())
					.getEmail(), ban.getBanDate(), ban.getNoDay(), ban
					.getReason(), userDao.findById(ban.getAdminId()).getEmail()));
		}

		return banRqs;

	}

	@Override
	public BanRq getBan(long id) {
		Ban ban = banDao.getBan(id);
		return new BanRq(ban.getId(), userDao.findById(ban.getUserId())
				.getEmail(), ban.getBanDate(), ban.getNoDay(), ban.getReason(),
				userDao.findById(ban.getAdminId()).getEmail());
	}

	@Override
	public void deleteBan(long id) {
		Ban ban = banDao.getBan(id);
		banDao.deleteBan(id);
		userDao.activateUser(ban.getUserId());
	}

	@Override
	public BanRq insertBan(BanRq banRq) throws DdException {
		User user = userDao.getUserByEmail(banRq.getUser());
		if (user == null) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Ban.USER_NOT_EXIST_CODE, Ban.USER_NOT_EXIST);
		}
		validateNoDay(banRq.getNoDay());
		validateBanDate(banRq.getBanDate());
		long userId = user.getUid();
		if(!checkDupUser(userId)) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Ban.USER_HAS_BANNED_CODE, Ban.USER_HAS_BANNED);
		}
		
		Ban ban = new Ban(userId, banRq.getBanDate(), banRq.getNoDay(),
				banRq.getReason(), ContextHolder.getInstance().getCurrentUser()
						.getUser().getUid());

		long id = banDao.insertBan(ban);
		Date truncateDate = DateUtils.truncate(
				DateUtils.addDays(banRq.getBanDate(), (int) banRq.getNoDay()),
				Calendar.DATE);
		if (DateUtils.isSameDay(new Date(), banRq.getBanDate())
				|| truncateDate.compareTo(DateUtils.truncate(new Date(),
						Calendar.DATE)) >= 0) {
			userDao.deactivateUser(userId);
		}
		banRq.setId(id);
		banRq.setAdmin(ContextHolder.getInstance().getCurrentUser()
						.getUser().getEmail());
		return banRq;
	}

	@Override
	public void updateBan(BanRq banRq) throws DdException {
		validateNoDay(banRq.getNoDay());
		validateBanDate(banRq.getBanDate());
		banDao.updateBan(new Ban(banRq.getId(), banRq.getBanDate(), banRq
				.getNoDay(), banRq.getReason()));
		Date truncateDate = DateUtils.truncate(
				DateUtils.addDays(banRq.getBanDate(), (int) banRq.getNoDay()),
				Calendar.DATE);
		if (DateUtils.isSameDay(new Date(), banRq.getBanDate())
				|| truncateDate.compareTo(DateUtils.truncate(new Date(),
						Calendar.DATE)) >= 0) {
			userDao.deactivateUser(banDao.getBan(banRq.getId()).getUserId());
		}
	}

	@Override
	public void saveBan(BanRq banRq) throws DdException {
		if (banRq.getId() == 0)
			insertBan(banRq);
		else
			updateBan(banRq);
	}
	
	@Override
	public long getPageNumber() {
		long pageNum = 0;
		pageNum = (long) Math.ceil((double) banDao.countBans()
				/ WebConstant.IMAGE_PER_PAGE);
		return pageNum;
	}
	
	@Override
	public List<BanRq> getBansByPage(int page) {
		List<Ban> bans = banDao.getBansByPage(
				page * WebConstant.ADMIN_BAN_PER_PAGE,
				WebConstant.ADMIN_BAN_PER_PAGE + 1);
		List<BanRq> banRqs = new ArrayList<BanRq>();
		
		for (Ban ban : bans) {
			banRqs.add(new BanRq(ban.getId(), userDao.findById(ban.getUserId())
					.getEmail(), ban.getBanDate(), ban.getNoDay(), ban
					.getReason(), userDao.findById(ban.getAdminId()).getEmail()));
		}

		return banRqs;
	}

	private void validateNoDay(long value) throws DdException {
		if (value <= 0)
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Ban.NO_DAY_INVALID_CODE, Ban.NO_DAY_INVALID);
	}

	private void validateBanDate(Date date) throws DdException {
		if (date == null) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Ban.BAN_DATE_INVALID_CODE, Ban.BAN_DATE_INVALID);
		}
	}
	
	private boolean checkDupUser(long id) {
		return banDao.checkDupUser(id);
	}

}
