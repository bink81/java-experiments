package patterns.factory.concise;

public abstract class Product {
	private final String size;

	@FunctionalInterface
	public interface Factory {
		Product produce(String type);
	}

	protected Product(String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public abstract int getPrice();

	@Override
	public String toString() {
		return "Product [size: " + size + ", price: " + getPrice()
				+ "]";
	}
}