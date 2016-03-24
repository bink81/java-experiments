package patterns.factory;

public final class Product2 extends Product {
	public Product2(String size) {
		super(size);
	}

	@Override
	public int getPrice() {
		return 20;
	}
}