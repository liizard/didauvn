package admin.dao;

import java.util.List;

import admin.model.Ban;

public interface BanDao {

	public Ban getBan(long id);

	public List<Ban> getBans();

	public long insertBan(final Ban ban);

	public void updateBan(Ban ban);

	public void deleteBan(long id);

	public void updateUserStatusByBans();

	public List<Ban> getBansByPage(int page, int count);

	public long countBans();

	public boolean checkDupUser(long id);

}