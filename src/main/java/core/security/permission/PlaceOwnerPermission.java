package core.security.permission;

import java.util.List;

import org.springframework.security.core.Authentication;

import core.context.ContextHolder;

public class PlaceOwnerPermission implements IPermission {

	public PlaceOwnerPermission() {
	}

	@Override
	public boolean isAllowed(Authentication authentication,
			Object targetDomainObject) {
		List<Long> ids = ContextHolder.getInstance().getUserPlaceOwner();
		long placeId = (Long) targetDomainObject;
		return ids.contains(placeId);
	}

}
