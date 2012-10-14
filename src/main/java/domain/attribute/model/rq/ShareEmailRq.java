package domain.attribute.model.rq;

import domain.attribute.model.Email;

public class ShareEmailRq {
	private Email[] emails;
	private String message;

	public ShareEmailRq() {
		message = new String();
	}

	public Email[] getEmails() {
		return emails;
	}

	public void setEmails(Email[] emails) {
		this.emails = emails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
