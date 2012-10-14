package core.security.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class NewsPermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public NewsPermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		long newsId = (Long) targetDomainObject;
		int rs = permissionDao.checkNewsPlaceManager(newsId, ContextHolder
				.getInstance().getCurrentUserId());
		if (rs == 1) {
			return true;
		}
		return false;
	}

}
