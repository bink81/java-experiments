package algorithms.greedy;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;

public class KnapsackTest {

	private static final double DELTA = 0.0001;

	private static final int ZERO_SPACE = 0;

	private static final int SOME_SPACE = 10;

	@Test
	public void testZeroSpace() throws Exception {
		int[] values = new int[] {};
		int[] weights = new int[] {};
		double actual = Knapsack.getFractionalBestValue(ZERO_SPACE, values, weights);

		Assert.assertEquals(0, actual, DELTA);
	}

	@Test
	public void testSomeSpaceButItemsWithNoWeight() throws Exception {
		int[] values = new int[] { 500 };
		int[] weights = new int[] { 0 };
		double actual = Knapsack.getFractionalBestValue(SOME_SPACE, values, weights);

		Assert.assertEquals(0, actual, DELTA);
	}

	@Test
	public void testSomeSpaceButNoItems() throws Exception {
		int[] values = new int[] {};
		int[] weights = new int[] {};
		double actual = Knapsack.getFractionalBestValue(SOME_SPACE, values, weights);

		Assert.assertEquals(0, actual, DELTA);
	}

	@Test
	public void test50() throws Exception {
		int[] values = new int[] { 60, 100, 120 };
		int[] weights = new int[] { 20, 50, 30 };
		double actual = Knapsack.getFractionalBestValue(50, values, weights);

		Assert.assertEquals(180, actual, DELTA);
	}

	@Test
	public void test10() throws Exception {
		int[] values = new int[] { 500 };
		int[] weights = new int[] { 30 };
		double actual = Knapsack.getFractionalBestValue(10, values, weights);

		Assert.assertEquals(166.6667, actual, DELTA);
	}

	@Test
	public void testRandomValues() throws Exception {
		final int recordSize = 1000;
		int[] values = generateRandomValues(recordSize);
		int[] weights = generateRandomValues(recordSize);

		Knapsack.getFractionalBestValue(100, values, weights);
	}

	private int[] generateRandomValues(int recordSize) {
		List<Integer> randomNumbers =
				new RandomIntegerGenerator(recordSize, 100).toList();
		int[] values = new int[recordSize];
		for (int i = 0; i < values.length; i++) {
			values[i] = randomNumbers.get(i);
		}
		return values;
	}
}
