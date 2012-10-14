package core.mail;

import java.util.HashMap;

public interface MailService {
	
	public void send(String from, String to, String locale,
			HashMap<String, Object> data, TemplateEnum templateEnum);

}