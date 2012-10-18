package domain.user.social;

import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import domain.user.dao.UserDao;
import domain.user.model.User;

public class AccountConnectionSignUp implements ConnectionSignUp {

	private static final Logger RUNTIME_LOGGER = Logger
			.getLogger("runtimeLogger");
	private static final Logger LOGGER = Logger.getLogger("mainLogger");
	
    private final UserDao userDao;
    

    public AccountConnectionSignUp(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute(Connection<?> connection) {
    	try {
    	UserProfile profile = connection.fetchUserProfile();
        User user = new User();
        user.setEmail(profile.getEmail());
        user.setName(profile.getName());
        long id = 0;
        id = userDao.insertForFB(user);
        return String.valueOf(id);
    	} catch (Exception e) {
    		LOGGER.error("Facebook Get Info Error");
			RUNTIME_LOGGER.error("Facebook Get Info Error", e);
			return "0";
		}
    }
	
}
