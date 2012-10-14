package core.security.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class PlacePermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public PlacePermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		long placeId = (Long) targetDomainObject;
		int rs = permissionDao.checkPlaceManager(placeId, ContextHolder
				.getInstance().getCurrentUserId());
		if (rs == 1) {
			return true;
		}
		return false;
	}

}
