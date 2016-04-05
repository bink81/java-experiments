package patterns.factory.method;

public class FactoryMethodClient {
	public static void main(String[] args) {
		ProductFactory factory1 = new ProductFactory1();
		Product product1 = factory1.create();
		System.out.println(product1.getName());
		ProductFactory factory2 = new ProductFactory2();
		Product product2 = factory2.create();
		System.out.println(product2.getName());
	}
}
