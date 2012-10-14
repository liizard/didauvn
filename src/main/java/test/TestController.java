package test;

import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.context.ContextHolder;
import core.job.SendMailJob;
import core.job.executor.AsyncJobExecutor;
import core.mail.MailService;
import core.mail.TemplateEnum;

@Controller
public class TestController {
	
	@Autowired
	AsyncJobExecutor executor;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String test() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		String locale = ContextHolder.getInstance().getCurrentLang().getLang();
		String token = RandomStringUtils.randomAlphanumeric(11);
		data.put("name", "abcd");
		data.put("uid", "12345");
		data.put("token", token);
		SendMailJob job = new SendMailJob(mailService, "vcthanh24@gmail.com", "vcthanh24@gmail.com", locale, data,
				TemplateEnum.CONFIRM_REGISTER);
		executor.execute(job);
		return "test";
	}
}
