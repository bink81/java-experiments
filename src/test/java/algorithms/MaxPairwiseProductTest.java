package algorithms;

import org.junit.Test;

import junit.framework.Assert;

public class MaxPairwiseProductTest {
	@Test
	public void testZero() throws Exception {
		int[] numbers = { 0 };
		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);
		Assert.assertEquals(0, actual);
	}

	@Test
	public void testOne() throws Exception {
		int[] numbers = { 1 };
		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);
		Assert.assertEquals(0, actual);
	}

	@Test
	public void testTwo() throws Exception {
		int[] numbers = { 1, 2 };
		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);
		Assert.assertEquals(2, actual);
	}

	@Test
	public void testTree() throws Exception {
		int[] numbers = { 1, 2, 3 };
		int actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);
		Assert.assertEquals(6, actual);
	}
}
