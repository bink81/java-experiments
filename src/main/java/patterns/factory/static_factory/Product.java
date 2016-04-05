package patterns.factory.static_factory;

public class Product {
	private final String name;

	protected Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
