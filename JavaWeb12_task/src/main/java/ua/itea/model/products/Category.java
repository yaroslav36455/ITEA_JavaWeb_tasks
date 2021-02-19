package ua.itea.model.products;

public enum Category {
	MEDIA("Медиа"),
	OFFICE("Офис"),
	CAD("САПР");
	
	private final String name;
	
	private Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
