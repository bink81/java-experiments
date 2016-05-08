package algorithms.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class KnapsackTest {

	@Test
	public void testNotEnoughSpace() throws Exception {
		int[] weights = new int[] { 3 };

		int actual = Knapsack.optimalWeight(1, weights);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testExact() throws Exception {
		int exactMatch = 3;
		int[] weights = new int[] { exactMatch };

		int actual = Knapsack.optimalWeight(exactMatch, weights);

		Assert.assertEquals(exactMatch, actual);
	}

	@Test
	public void testOptimalWeight() throws Exception {
		int[] weights = new int[] { 1, 4, 8 };

		int actual = Knapsack.optimalWeight(10, weights);

		Assert.assertEquals(9, actual);
	}
}
