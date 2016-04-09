package algorithms;

import org.junit.Test;

import junit.framework.Assert;

public class MaxPairwiseProductTest {
	@Test
	public void testZero() throws Exception {
		Integer[] numbers = { 0 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testOne() throws Exception {
		Integer[] numbers = { 1 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testTwo() throws Exception {
		Integer[] numbers = { 1, 2 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testTree() throws Exception {
		Integer[] numbers = { 1, 2, 3 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testFour() throws Exception {
		Integer[] numbers = { 1, 2, 3, 4 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(12, actual);
	}

	@Test
	public void testUnorderedFour() throws Exception {
		Integer[] numbers = { 1, 2, 3, 2 };

		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(6, actual);
	}
}
