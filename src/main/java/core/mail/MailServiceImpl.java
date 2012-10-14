package core.mail;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


@Component
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private MailSender mailSender;
	
	private SimpleMailMessage getTemplateMail(String from, String to,
			String subject, Locale locale, HashMap<String, Object> data,
			TemplateEnum templateEnum) {
		SimpleMailMessage templateMailMessage = new SimpleMailMessage();
		templateMailMessage.setTo(to);
		templateMailMessage.setFrom(from);
		templateMailMessage.setReplyTo(from);
		templateMailMessage.setSentDate(new Date());
		templateMailMessage.setSubject(subject);
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, templateEnum.getResourcePath() + "_" + locale
						+ ".vm", data);
		templateMailMessage.setText(text);
		System.out.println(text);
		return templateMailMessage;
	}
	
	@Override
	public void send(String from, String to, String locale,
			HashMap<String, Object> data,
			TemplateEnum templateEnum) {
		Locale loc = new Locale(locale);
		String subject = messageSource.getMessage(templateEnum.getSubject(), null, loc);
		mailSender.send(getTemplateMail(from, to, subject, loc, data, templateEnum));
	}

}
