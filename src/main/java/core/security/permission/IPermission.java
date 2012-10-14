package core.security.permission;

import org.springframework.security.core.Authentication;

public interface IPermission {
    boolean isAllowed(Authentication authentication, Object targetDomainObject);
}
