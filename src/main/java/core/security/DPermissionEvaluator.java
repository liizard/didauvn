package core.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import core.security.permission.IPermission;

public class DPermissionEvaluator implements PermissionEvaluator {
	private Map<String, IPermission> permissionNameToPermissionMap = new HashMap<String, IPermission>();

	protected DPermissionEvaluator() {
	}

	public DPermissionEvaluator(
			Map<String, IPermission> permissionNameToPermissionMap) {
		this.permissionNameToPermissionMap = permissionNameToPermissionMap;
	}

	@Override
	@Transactional
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		boolean hasPermission = false;
		if (canHandle(authentication, targetDomainObject, permission)) {
			hasPermission = checkPermission(authentication, targetDomainObject,
					(String) permission);
		}
		return hasPermission;
	}

	private boolean canHandle(Authentication authentication,
			Object targetDomainObject, Object permission) {
		return targetDomainObject != null && authentication != null
				&& permission instanceof String;
	}

	private boolean checkPermission(Authentication authentication,
			Object targetDomainObject, String permissionKey) {
		verifyPermissionIsDefined(permissionKey);
		IPermission permission = permissionNameToPermissionMap
				.get(permissionKey);
		return permission.isAllowed(authentication, targetDomainObject);
	}

	private void verifyPermissionIsDefined(String permissionKey) {
		if (!permissionNameToPermissionMap.containsKey(permissionKey)) {
			throw new RuntimeException("No permission with key "
					+ permissionKey + " is defined in "
					+ this.getClass().toString());
		}
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		throw new RuntimeException(
				"Id and Class permissions are not supperted by "
						+ this.getClass().toString());
	}
}
