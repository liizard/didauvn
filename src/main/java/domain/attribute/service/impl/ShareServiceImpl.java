package domain.attribute.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import core.context.ContextHolder;
import core.job.SendMailJob;
import core.job.executor.AsyncJobExecutor;
import core.mail.MailService;
import core.mail.TemplateEnum;
import core.util.SecurityUtil;
import domain.attribute.model.rq.ShareEmailRq;
import domain.attribute.service.ShareService;
import domain.place.dao.PlaceDao;
import domain.place.model.PlaceDetail;

@Service("shareService")
public class ShareServiceImpl implements ShareService {
	@Autowired
	private MailService mailService;

	@Autowired
	private PlaceDao placeDao;

	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public void sharePlaceViaEmail(long placeId, ShareEmailRq shareEmailRq) {
		AsyncJobExecutor executor = new AsyncJobExecutor();
		HashMap<String, Object> emailData = new HashMap<String, Object>();
		String locale = ContextHolder.getInstance().getCurrentLang().getLang();
		PlaceDetail placeDetail = placeDao.getPlaceDetail(placeId, locale);
		emailData.put("userName", ContextHolder.getInstance().getCurrentUser()
				.getUser().getName());
		emailData.put("placeId", placeId);
		emailData.put("placeName", placeDetail.getName());
		if (shareEmailRq.getMessage() == null
				|| shareEmailRq.getMessage().isEmpty()) {
			if ("en".equals(locale)) {
				shareEmailRq
						.setMessage("Hey, check out this place i've just found!");
			} else {
				shareEmailRq.setMessage("Xem qua chỗ này nhé!");
			}

		}
		emailData.put("message", shareEmailRq.getMessage());
		StringBuilder address = new StringBuilder();
		address.append(placeDetail.getAddress());
		address.append(", ");
		address.append(placeDetail.getStreetName());
		address.append(", ");
		address.append(placeDetail.getWardName());
		address.append(", ");
		address.append(placeDetail.getDistrictName());
		emailData.put("placeAddress", address);
		for (int i = 0; i < shareEmailRq.getEmails().length; i++) {
			String email = shareEmailRq.getEmails()[i].getEmail();
			if (SecurityUtil.checkEmail(email)) {
				SendMailJob job = new SendMailJob(mailService, ContextHolder
						.getInstance().getCurrentUser().getUser().getEmail(),
						email, locale, emailData,
						TemplateEnum.SHARE_PLACE_VIA_EMAIL);
				executor.execute(job);
			}
		}

	}

}
