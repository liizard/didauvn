package core.security.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class ImagePermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public ImagePermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		long imageId = (Long) targetDomainObject;
		int rs = permissionDao.checkImgPlaceManager(imageId, ContextHolder
				.getInstance().getCurrentUserId());
		if (rs == 1) {
			return true;
		}
		return false;
	}

}
