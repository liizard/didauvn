package domain.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.user.service.UserService;

@Controller
public class ActivationController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/verify/{uid}/{token}")
	@ResponseBody
	public String verifyActivation(@PathVariable("uid") long uid, @PathVariable("token") String token) {			
		if (userService.isSuccessfulActivation(uid, token)){
			return "success";			
		}
		return "Invalid URL!!!"; 	
	}

}
