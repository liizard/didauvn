package core.mail;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MailController {

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="/mail")
	@ResponseBody
	public String sendMail() {
		String locale = "en";
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("userName", "admin");
		data.put("email", "abc@def");
		data.put("password", "123456");
		
		mailService.send("ngocnghechvt@gmail.com", "ngocnghechvt@gmail.com", locale, data, TemplateEnum.CONFIRM_REGISTER);
		
		
		return "success";
	}
	
}
