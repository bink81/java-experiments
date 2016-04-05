package patterns.factory.static_factory;

import org.junit.Test;

import junit.framework.Assert;

public class StaticFactoryClientTest {
	@Test
	public void testProduct1Creation() throws Exception {
		Product product = ProductCreator.create(true);

		Assert.assertEquals(Product1.NAME, product.getName());
	}

	@Test
	public void testProduct2Creation() throws Exception {
		Product product = ProductCreator.create(false);

		Assert.assertEquals(Product2.NAME, product.getName());
	}
}
