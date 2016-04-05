package patterns.factory.method;

public class Product {
	private final String name;

	protected Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
