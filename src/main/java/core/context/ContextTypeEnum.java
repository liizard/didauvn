package core.context;

public enum ContextTypeEnum {
	INITDEFAULT("initDefault"), INITUSER("initUser"), CURRLANG("currentLang"), USER_PLACE_OWNER(
			"userPlaceOwner"), USER_PLACE_MANAGER("userPlaceManager"), PLACE_SEARCH_RQ(
			"placeSearchRq"), USER_PLACE_WOW("userPlaceWow"), USER_PLACE_VISITED(
			"userPlaceVisited"), IS_ADMIN("isAdmin");

	private String key;

	ContextTypeEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
