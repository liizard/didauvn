package domain.attribute.model.rq;


public class TagRq {

	private String name;

	public TagRq(String name) {
		super();
		this.name = name;
	}
	
	public TagRq() {
		this("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
