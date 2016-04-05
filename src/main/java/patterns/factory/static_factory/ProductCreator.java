package patterns.factory.static_factory;

public class ProductCreator {

	public static Product create(boolean chooseOne) {
		if (chooseOne) {
			return new Product1();
		}
		else {
			return new Product2();
		}
	}
}
