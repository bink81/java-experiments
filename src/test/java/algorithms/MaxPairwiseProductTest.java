package algorithms;

import org.junit.Test;

import junit.framework.Assert;
import utils.EventAnalyzer;

public class MaxPairwiseProductTest {
	@Test
	public void testZero() throws Exception {
		int[] numbers = { 0 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testOne() throws Exception {
		int[] numbers = { 1 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testTwo() throws Exception {
		int[] numbers = { 1, 2 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testWithHugeIntegers() throws Exception {
		int[] numbers = { 2000000000, 2000000000 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertTrue("Result must be a huge positive value", actual > 0);
	}

	@Test
	public void testTree() throws Exception {
		int[] numbers = { 1, 2, 3 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testFour() throws Exception {
		int[] numbers = { 1, 2, 3, 4 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(12, actual);
	}

	@Test
	public void testUnorderedFour() throws Exception {
		int[] numbers = { 1, 2, 3, 2 };

		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testBigArray() throws Exception {
		int[] numbers = new int[20000000];
		EventAnalyzer eventAnalyzer = new EventAnalyzer();

		eventAnalyzer.before();
		long actual = MaxPairwiseProduct.getMaxPairwiseProduct(numbers);
		eventAnalyzer.after();

		eventAnalyzer.report();
		Assert.assertEquals(0, actual);
	}
}
