package admin.service;

import java.util.List;

import admin.model.req.BanRq;
import core.exception.DdException;

public interface BanService {

	public List<BanRq> getBans();

	public BanRq getBan(long id);

	public void deleteBan(long id);

	public void updateBan(BanRq banRq) throws DdException;

	public BanRq insertBan(BanRq banRq) throws DdException;

	public void saveBan(BanRq banRq) throws DdException;

	public long getPageNumber();

	public List<BanRq> getBansByPage(int page);

}