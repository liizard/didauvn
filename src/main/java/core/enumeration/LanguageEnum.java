package core.enumeration;

public enum LanguageEnum {
	VI("Tieng Viet"), EN("English");
	
	private String name;
	
	private LanguageEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getLang() {
		return this.toString().toLowerCase();
	}
	
	
}
