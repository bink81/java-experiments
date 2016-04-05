package patterns.factory.simple;

public class SimpleClient {
	public static void main(String[] args) {
		Product product1 = Product.createProduct1();
		System.out.println(product1.getName());
		Product product2 = Product.createProduct2();
		System.out.println(product2.getName());
	}
}
