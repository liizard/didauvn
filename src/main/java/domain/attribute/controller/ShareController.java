package domain.attribute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.attribute.model.rq.ShareEmailRq;
import domain.attribute.service.ShareService;

@Controller
public class ShareController {
/*
	@Autowired
	private ShareService shareService;
	
	@RequestMapping(value = "share/email/{placeid}", method = RequestMethod.POST)
	@ResponseBody
	public void sendEmalAll(@RequestBody ShareEmailRq shareEmailRq,
			@PathVariable("placeid") long placeId) {
		shareService.sharePlaceViaEmail(placeId, shareEmailRq);	
	}

	@RequestMapping(value = "share/email/template", method = RequestMethod.GET)
	@ResponseBody
	public ShareEmailRq getShareEmailTemplate() {
		return new ShareEmailRq();
	}*/
}
