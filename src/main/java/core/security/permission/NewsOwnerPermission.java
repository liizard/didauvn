package core.security.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import core.context.ContextHolder;
import core.security.dao.PermissionDao;

public class NewsOwnerPermission implements IPermission {

	@Autowired
	private PermissionDao permissionDao;

	public NewsOwnerPermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		List<Long> ids = ContextHolder.getInstance().getUserPlaceOwner();
		long newsId = (Long) targetDomainObject;
		long placeId = permissionDao.checkNewsPlaceOwner(newsId);
		return ids.contains(placeId);
	}
}
