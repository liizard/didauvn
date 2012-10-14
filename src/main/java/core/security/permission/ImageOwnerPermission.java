package core.security.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class ImageOwnerPermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public ImageOwnerPermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		List<Long> ids = ContextHolder.getInstance().getUserPlaceOwner();
		long imageId = (Long) targetDomainObject;
		long placeId = permissionDao.checkImgPlaceOwner(imageId);
		return ids.contains(placeId);
	}
}
