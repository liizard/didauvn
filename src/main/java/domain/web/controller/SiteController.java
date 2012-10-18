/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteController {
	private final static String VIEW_BASE = "";
	
	/* ADMIN */
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String admin() {
		return VIEW_BASE + "/admin";
	}
	
	@RequestMapping(value = "admin/view", method = RequestMethod.GET)
	public String adminView() {
		return VIEW_BASE + "/admin/adminview";
	}
	
	@RequestMapping(value = "admin/user", method = RequestMethod.GET)
	public String adminUser() {
		return VIEW_BASE + "/admin/adminuser";
	}
	
	@RequestMapping(value = "admin/feedback", method = RequestMethod.GET)
	public String adminFeedback() {
		return VIEW_BASE + "/admin/adminfeedback";
	}
	
	@RequestMapping(value = "admin/report", method = RequestMethod.GET)
	public String adminReport() {
		return VIEW_BASE + "/admin/adminreport";
	}

	/* HOME */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		return VIEW_BASE + "/home";
	}

	@RequestMapping(value = "home/view", method = RequestMethod.GET)
	public String homeView() {
		return VIEW_BASE + "/home/homeview";
	}
	
	@RequestMapping(value = "home/tos", method = RequestMethod.GET)
	public String homeTOS() {
		return VIEW_BASE + "/home/hometos";
	}
	
	@RequestMapping(value = "home/guide", method = RequestMethod.GET)
	public String homeGuide() {
		return VIEW_BASE + "/home/homeguide";
	}

	/* GOAROUND */
	@RequestMapping(value = "goaround", method = RequestMethod.GET)
	public String goaround() {
		return VIEW_BASE + "/goaround";
	}

	@RequestMapping(value = "goaround/news", method = RequestMethod.GET)
	public String goaroundNews() {
		return VIEW_BASE + "/goaround/goaroundnews";
	}

	@RequestMapping(value = "goaround/offers", method = RequestMethod.GET)
	public String goaroundOffers() {
		return VIEW_BASE + "/goaround/goaroundoffers";
	}

	@RequestMapping(value = "goaround/gallery", method = RequestMethod.GET)
	public String goaroundGallery() {
		return VIEW_BASE + "/goaround/goaroundgallery";
	}

	/* SEARCH */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search() {
		return VIEW_BASE + "/search";
	}

	@RequestMapping(value = "search/result", method = RequestMethod.GET)
	public String searchResult() {
		return VIEW_BASE + "/search/searchresult";
	}

	/* PLACE */
	@RequestMapping(value = "place", method = RequestMethod.GET)
	public String place() {
		return VIEW_BASE + "/place";
	}

	@RequestMapping(value = "placeview", method = RequestMethod.GET)
	public String placeView() {
		return VIEW_BASE + "/place/placeview";
	}

	@RequestMapping(value = "placenew", method = RequestMethod.GET)
	public String placeNew() {
		return VIEW_BASE + "/place/placenew";
	}

	@RequestMapping(value = "placesetting", method = RequestMethod.GET)
	public String placeSetting() {
		return VIEW_BASE + "/place/placesetting";
	}

	@RequestMapping(value = "placeupdate/general", method = RequestMethod.GET)
	public String generalUpdate() {
		return VIEW_BASE + "/place/generalupdate";
	}

	@RequestMapping(value = "placeupdate/additional", method = RequestMethod.GET)
	public String additionalUpdate() {
		return VIEW_BASE + "/place/additionalupdate";
	}

	@RequestMapping(value = "placeupdate/image", method = RequestMethod.GET)
	public String galleryUpdate() {
		return VIEW_BASE + "/place/imageupdate";
	}

	@RequestMapping(value = "placeupdate/video", method = RequestMethod.GET)
	public String videoUpdate() {
		return VIEW_BASE + "/place/videoupdate";
	}

//	@RequestMapping(value = "placeupdate/news", method = RequestMethod.GET)
//	public String newsUpdate() {
//		return VIEW_BASE + "/place/newsupdate";
//	}

	/* USER */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String user() {
		return VIEW_BASE + "/user";
	}

//	@RequestMapping(value = "user/view", method = RequestMethod.GET)
//	public String userView() {
//		return VIEW_BASE + "/user/userview";
//	}

//	@RequestMapping(value = "user/general", method = RequestMethod.GET)
//	public String userGeneral() {
//		return VIEW_BASE + "/user/usergeneral";
//	}

	@RequestMapping(value = "user/place", method = RequestMethod.GET)
	public String userPlace() {
		return VIEW_BASE + "/user/userplace";
	}
	
	@RequestMapping(value = "user/manager", method = RequestMethod.GET)
	public String userManager() {
		return VIEW_BASE + "/user/usermanager";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public String login() {
		return VIEW_BASE + "/user/userlogin";
	}

	@RequestMapping(value = "user/register", method = RequestMethod.GET)
	public String register() {
		return VIEW_BASE + "/user/userregister";
	}
	
//	@RequestMapping(value = "user/message", method = RequestMethod.GET)
//	public String message() {
//		return VIEW_BASE + "/user/usermessage";
//	}

}
