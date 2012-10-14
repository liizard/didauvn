package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.model.req.BanRq;
import admin.service.BanService;
import core.exception.DdException;

@Controller
@RequestMapping("admin/data/ban")
public class BanController {
	@Autowired
	BanService banService;

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public BanRq insertBan(@RequestBody BanRq banView) throws DdException {
		return banService.insertBan(banView);
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateBan(@RequestBody BanRq banView) throws DdException {
		banService.updateBan(banView);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveBan(@RequestBody BanRq banView) throws DdException {
		banService.saveBan(banView);
	}

	@ResponseBody
	@RequestMapping(value = "/del/{banid}", method = RequestMethod.POST)
	public void delBan(@PathVariable("banid") long banId) {
		banService.deleteBan(banId);
	}

	@ResponseBody
	@RequestMapping(value = "/get/{banid}", method = RequestMethod.POST)
	public BanRq getBan(@PathVariable("banid") long banId) {
		return banService.getBan(banId);

	}

	@ResponseBody
	@RequestMapping(value = "/get/new", method = RequestMethod.POST)
	public BanRq getNewBan() {
		return new BanRq();
	}

	@ResponseBody
	@RequestMapping(value = "/gets", method = RequestMethod.POST)
	public List<BanRq> getBans() {
		return banService.getBans();
	}

	@ResponseBody
	@RequestMapping(value = "/getbypage/{page}", method = RequestMethod.POST)
	public List<BanRq> getImageByPage(@PathVariable("page") int page) {
		return banService.getBansByPage(page);
	}

	@ResponseBody
	@RequestMapping(value = "/getpagecount", method = RequestMethod.POST)
	public long getImagesCount() {
		return banService.getPageNumber();
	}

}
