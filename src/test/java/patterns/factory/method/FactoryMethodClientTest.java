package patterns.factory.method;

import org.junit.Test;

import org.junit.Assert;

public class FactoryMethodClientTest {
	@Test
	public void testProduct1Creation() throws Exception {
		ProductFactory factory1 = new ProductFactory1();
		Product product1 = factory1.create();

		Assert.assertEquals(Product1.NAME, product1.getName());
	}

	@Test
	public void testProduct2Creation() throws Exception {
		ProductFactory factory2 = new ProductFactory2();
		Product product2 = factory2.create();

		Assert.assertEquals(Product2.NAME, product2.getName());
	}

}
