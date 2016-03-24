package patterns.factory;

public final class Shop {

	private Product.Factory factory;
	private long total;

	public void setSupplier(Product.Factory factory) {
		this.factory = factory;
	}

	public Product orderProduct(String type) {
		final Product product = factory.make(type);
		setTotal(getTotal() + product.getPrice());
		return product;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}