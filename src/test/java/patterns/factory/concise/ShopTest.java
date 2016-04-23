package patterns.factory.concise;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import patterns.factory.concise.Product;
import patterns.factory.concise.Product1;
import patterns.factory.concise.Product2;
import patterns.factory.concise.Shop;

public class ShopTest {

	private static final String SIZE_1 = "1";
	private static final String SIZE_2 = "2";
	private Shop shop;

	@Before
	public void setUp() throws Exception {
		shop = new Shop();
	}

	@Test
	public void testOrderProduct1() throws Exception {
		setType1();

		final Product product = shop.orderProduct(SIZE_1);

		long total = shop.getTotal();
		Assert.assertEquals(SIZE_1, product.getSize());
		Assert.assertEquals(10, total);
	}

	@Test
	public void testOrderProduct2() throws Exception {
		setType2();

		final Product product = shop.orderProduct(SIZE_2);

		long total = shop.getTotal();
		Assert.assertEquals(SIZE_2, product.getSize());
		Assert.assertEquals(20, total);
	}

	@Test
	public void testOrderTwoProducts1() throws Exception {
		setType1();
		final Product product1 = shop.orderProduct(SIZE_1);
		final Product product2 = shop.orderProduct(SIZE_2);

		long total = shop.getTotal();
		Assert.assertEquals(SIZE_1, product1.getSize());
		Assert.assertEquals(SIZE_2, product2.getSize());
		Assert.assertEquals(20, total);
	}

	@Test
	public void testOrderProducts1and2() throws Exception {
		setType1();
		final Product product1 = shop.orderProduct(SIZE_1);
		setType2();
		final Product product2 = shop.orderProduct(SIZE_2);

		long total = shop.getTotal();
		Assert.assertEquals(SIZE_1, product1.getSize());
		Assert.assertEquals(SIZE_2, product2.getSize());
		Assert.assertEquals(30, total);
	}

	private void setType1() {
		shop.setType(Product1::new);
	}

	private void setType2() {
		shop.setType(Product2::new);
	}
}
