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
package domain.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import core.exception.DdException;
import domain.place.model.PlaceGeneral;
import domain.user.model.User;
import domain.user.model.UserGeneral;
import domain.user.model.UserSession;
import domain.user.service.UserService;

@Controller
@RequestMapping("data/user")
public class UserController {
	@Autowired
	private UserService userService;
//	private ObjectMapper mapper = new ObjectMapper();

	// *Create new User
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@ResponseBody
	public User newUser(WebRequest request) throws IOException, ParseException {
		return userService.returnOAuthUser(request);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public void newUser(@RequestBody User user, WebRequest request)
			throws DdException, IOException {
		userService.insert(user, request);
	}

	// get current user
	@RequestMapping(value = "/current", method = RequestMethod.POST)
	@ResponseBody
	public UserSession getCurrentUser() {
		return userService.getCurrentUser();
	}

//	@RequestMapping(value = "/password/change", method = RequestMethod.POST)
//	@ResponseBody
//	public void changePassword(@RequestBody Password password)
//			throws DdException {
//		userService.changePassword(password);
//	}
//
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public void update(@RequestBody User user) throws ParseException,
//			DdException {
//
//		userService.updateProfile(user);
//	}
//
//	@RequestMapping(value = "/password/new", method = RequestMethod.GET)
//	@ResponseBody
//	public Password newPasswordForm() {
//		return new Password();
//	}

	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public UserGeneral getUserProfile(@PathVariable("userId") long uid) {
		return userService.getUser(uid);
	}

	@RequestMapping(value = "/getplaceowner", method = RequestMethod.GET)
	@ResponseBody
	public List<PlaceGeneral> getPlaceOwner() {
		return userService.getPlaceOwner();
	}

	@RequestMapping(value = "/getplacemanager", method = RequestMethod.GET)
	@ResponseBody
	public List<PlaceGeneral> getPlaceManager() {
		return userService.getPlaceManager();
	}
	
//	@RequestMapping(value = "/getplacewow", method = RequestMethod.GET)
//	@ResponseBody
//	public List<PlaceGeneral> getPlaceWow() {
//		return userService.getPlaceWow();
//	}

//	@RequestMapping(value = "/upimg", method = RequestMethod.POST)
//	public String upImg(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) {
//		try {
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			MultipartFile imgFile = multipartRequest.getFile("file");
//			long imageId;
//			imageId = userService.insertImage(imgFile);
//			List<Long> images = new ArrayList<Long>();
//			response.setContentType("text/plain");
//			images.add(imageId);
//			response.getWriter().write(mapper.writeValueAsString(images));
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
