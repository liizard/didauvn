package core.security.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class VideoPermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public VideoPermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		long videoId = (Long) targetDomainObject;
		int rs = permissionDao.checkVideoPlaceManager(videoId, ContextHolder
				.getInstance().getCurrentUserId());
		if (rs == 1) {
			return true;
		}
		return false;
	}
}
