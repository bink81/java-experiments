package patterns.factory.method;

public class ProductFactory1 implements ProductFactory {

	@Override
	public Product create() {
		return new Product1();
	}
}
