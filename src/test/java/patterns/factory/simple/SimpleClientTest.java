
package patterns.factory.simple;

import org.junit.Test;

import org.junit.Assert;

public class SimpleClientTest {
	@Test
	public void testProduct1Creation() throws Exception {
		Product product1 = Product.createProduct1();

		Assert.assertEquals(Product1.NAME, product1.getName());
	}

	@Test
	public void testProduct2Creation() throws Exception {
		Product product2 = Product.createProduct2();

		Assert.assertEquals(Product2.NAME, product2.getName());
	}
}
