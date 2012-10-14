package core.mail;

public enum TemplateEnum {
	
	CONFIRM_REGISTER("core/mail/vm/register","mail.register"), RESET_PASSWORD("core/mail/vm/reset","mail.reset"),
	SHARE_PLACE_VIA_EMAIL("core/mail/vm/share","mail.share");
	
	private String resourcePath;
	private String subject;

	private TemplateEnum(String resourcePath, String subject) {
		this.resourcePath = resourcePath;
		this.subject = subject;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public String getSubject() {
		return subject;
	}

}
