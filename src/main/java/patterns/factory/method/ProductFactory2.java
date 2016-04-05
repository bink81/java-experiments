package patterns.factory.method;

public class ProductFactory2 implements ProductFactory {

	@Override
	public Product create() {
		return new Product2();
	}
}
