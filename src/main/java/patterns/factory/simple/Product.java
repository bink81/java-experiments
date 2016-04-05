package patterns.factory.simple;

public class Product {
	private final String name;

	protected Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Product1 createProduct1() {
		return new Product1();
	}

	public static Product2 createProduct2() {
		return new Product2();
	}
}
