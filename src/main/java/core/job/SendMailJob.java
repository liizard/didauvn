package core.job;

import java.util.HashMap;

import core.mail.MailService;
import core.mail.TemplateEnum;

public class SendMailJob implements Job {

	private MailService mailService;
	private String from;
	private String to;
	private String locale;
	private HashMap<String, Object> data;
	private TemplateEnum templateEnum;

	public SendMailJob(MailService mailService, String from, String to, String locale,
			HashMap<String, Object> data, TemplateEnum templateEnum) {
		this.mailService = mailService;
		this.from = from;
		this.to = to;
		this.locale = locale;
		this.data = data;
		this.templateEnum = templateEnum;
	}

	@Override
	public void execute() {
		System.out.println("sent");
		mailService.send(from, to, locale, data, templateEnum);
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public TemplateEnum getTemplateEnum() {
		return templateEnum;
	}

	public void setTemplateEnum(TemplateEnum templateEnum) {
		this.templateEnum = templateEnum;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

}
