package core.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailSender {

	@Autowired
	private JavaMailSender mailSender;

	public MailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void send(final SimpleMailMessage mailMessage) {
		try {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(
							mimeMessage);
					if (mailMessage.getFrom() != null) {
						message.setFrom(mailMessage.getFrom());
					}
					if (mailMessage.getReplyTo() != null) {
						message.setReplyTo(mailMessage.getReplyTo());
					}
					if (mailMessage.getTo() != null) {
						message.setTo(mailMessage.getTo());
					}
					if (mailMessage.getSubject() != null) {
						message.setSubject(mailMessage.getSubject());
					}
					if (mailMessage.getSentDate() != null) {
						message.setSentDate(mailMessage.getSentDate());
					} else {
						message.setSentDate(java.util.Calendar.getInstance()
								.getTime());
					}
					if (mailMessage.getText() != null) {
						message.setText(mailMessage.getText());
					}

					message.setText(mailMessage.getText(), true);

				}
			};
			mailSender.send(preparator);
		} catch (Exception e) {
		}

	}

}
