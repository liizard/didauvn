package core.context;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import core.enumeration.LanguageEnum;
import domain.place.model.rq.PlaceSearchRq;
import domain.user.model.SecureUser;
import domain.user.service.UserSettingService;

public class ContextHolder {

	private Context context;

	private UserSettingService userSettingService;

	private static ContextHolder instance;

	private ContextHolder(final Context context,
			final UserSettingService userSettingService) {
		this.context = context;
		this.userSettingService = userSettingService;
	}

	public static ContextHolder createInstance(final Context context,
			final UserSettingService userSettingService) {
		if (instance == null) {
			synchronized (ContextHolder.class) {
				if (instance == null) {
					instance = new ContextHolder(context, userSettingService);
				}
			}
		}
		return instance;
	}

	public static ContextHolder getInstance() {
		Object initDefault = instance.context.get(ContextTypeEnum.INITDEFAULT);
		Object initUser = instance.context.get(ContextTypeEnum.INITUSER);

		if (initUser == null || ((Boolean) initUser) == false) {
			synchronized (ContextHolder.class) {
				if (initUser == null || ((Boolean) initUser) == false) {
					Object user = getNullableCurrentUser();
					if (user != null) {
						initUser();
						instance.context.add(ContextTypeEnum.INITDEFAULT, true);
						instance.context.add(ContextTypeEnum.INITUSER, true);
					}
				}
			}
		}

		if (initDefault == null || ((Boolean) initDefault) == false) {
			synchronized (ContextHolder.class) {
				if (initDefault == null || ((Boolean) initDefault) == false) {
					initDefault();
					instance.context.add(ContextTypeEnum.INITDEFAULT, true);
				}
			}
		}

		return instance;
	}

	private static void initDefault() {
		instance.setCurrentLang(LanguageEnum.VI);
		instance.setAdmin(false);
	}

	private static void initUser() {
		String lang = instance.userSettingService.getDbLang();
		if (lang != null) {
			instance.setCurrentLang(LanguageEnum.valueOf(lang.toUpperCase()));
		}

		// ADMIN ROLE
		instance.setAdmin(instance.getCurrentUser().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));

		List<Long> ownerIds = instance.userSettingService.getPlaceOwners();
		if (ownerIds != null) {
			instance.setUserPlaceOwner(ownerIds);
		}

		List<Long> managerIds = instance.userSettingService.getPlaceManagers();
		if (managerIds != null) {
			instance.setUserPlaceManager(managerIds);
		}

		List<Long> wowIds = instance.userSettingService.getPlaceWows();
		if (wowIds != null) {
			instance.setUserPlaceWow(wowIds);
		}
	}

	public void clear() {
		context.clear();
	}

	public void setCurrentLang(LanguageEnum lang) {
		context.add(ContextTypeEnum.CURRLANG, lang);
	}

	public LanguageEnum getCurrentLang() {
		Object obj = context.get(ContextTypeEnum.CURRLANG);
		return obj == null ? LanguageEnum.VI : (LanguageEnum) obj;
	}

	public SecureUser getCurrentUser() {
		try {
			SecureUser user = (SecureUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public long getCurrentUserId() {
		SecureUser user = getCurrentUser();
		return user == null ? 0 : user.getUser().getUid();
	}

	public void setUserPlaceOwner(List<Long> ids) {
		context.add(ContextTypeEnum.USER_PLACE_OWNER, ids);
	}

	public List<Long> getUserPlaceOwner() {
		List<Long> ids = (List<Long>) context
				.get(ContextTypeEnum.USER_PLACE_OWNER);
		return ids;
	}

	public void setUserPlaceManager(List<Long> ids) {
		context.add(ContextTypeEnum.USER_PLACE_MANAGER, ids);
	}

	public List<Long> getUserPlaceManager() {
		List<Long> ids = (List<Long>) context
				.get(ContextTypeEnum.USER_PLACE_MANAGER);
		return ids;
	}

	public void setUserPlaceWow(List<Long> ids) {
		context.add(ContextTypeEnum.USER_PLACE_WOW, ids);
	}

	public List<Long> getUserPlaceWow() {
		List<Long> ids = (List<Long>) context
				.get(ContextTypeEnum.USER_PLACE_WOW);
		return ids;
	}

	public void setUserPlaceVisited(List<Long> ids) {
		context.add(ContextTypeEnum.USER_PLACE_VISITED, ids);
	}

	public List<Long> getUserPlaceVisited() {
		List<Long> ids = (List<Long>) context
				.get(ContextTypeEnum.USER_PLACE_VISITED);
		return ids;
	}

	// *PlaceSearchRq
	public void setPlaceSearchRq(PlaceSearchRq placeSearchRq) {
		context.add(ContextTypeEnum.PLACE_SEARCH_RQ, placeSearchRq);
	}

	public PlaceSearchRq getPlaceSearchRq() {
		PlaceSearchRq placeSearchRq = (PlaceSearchRq) context
				.get(ContextTypeEnum.PLACE_SEARCH_RQ);
		if (placeSearchRq == null) {
			return new PlaceSearchRq();
		}
		return placeSearchRq;
	}
	
	private static SecureUser getNullableCurrentUser() {
		try {
			SecureUser user = (SecureUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	// ADMIN ROLE
	public boolean isAdmin() {
		return (Boolean) context.get(ContextTypeEnum.IS_ADMIN);
	}

	public void setAdmin(boolean isAdmin) {
		context.add(ContextTypeEnum.IS_ADMIN, isAdmin);
	}

}
