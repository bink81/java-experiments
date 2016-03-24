package patterns.factory;

public final class Product1 extends Product {
	public Product1(String size) {
		super(size);
	}

	@Override
	public int getPrice() {
		return 10;
	}
}