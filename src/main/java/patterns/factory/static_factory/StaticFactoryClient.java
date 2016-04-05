package patterns.factory.static_factory;

public class StaticFactoryClient {
	public static void main(String[] args) {
		Product product1 = ProductCreator.create(true);
		System.out.println(product1.getName());
		Product product2 = ProductCreator.create(false);
		System.out.println(product2.getName());
	}
}
