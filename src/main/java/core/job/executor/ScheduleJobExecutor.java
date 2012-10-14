package core.job.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import admin.dao.BanDao;

import domain.user.dao.UserDao;

@Component
public class ScheduleJobExecutor {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BanDao banDao;
	
	@Scheduled(cron="0 0 */2 * * ?")
	public void truncateUserActivation() {
		System.out.println("attempt to delete records from useractivation table...");
		userDao.truncateUserActivationTable();
	}
	
	@Scheduled(cron="0 0 0 1/1 * ?")
	public void updateUserStatusByBans() {
		System.out.println("attempt to update User records from Bans table...");
		banDao.updateUserStatusByBans();
		
	}
}
