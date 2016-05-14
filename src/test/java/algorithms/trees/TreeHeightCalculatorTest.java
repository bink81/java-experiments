package algorithms.trees;

import org.junit.Assert;
import org.junit.Test;

public class TreeHeightCalculatorTest {
	@Test
	public void test1() throws Exception {
		int[] parents = new int[] { -1, 0, 4, 0, 3 };
		TreeHeightCalculator heightCalculator = new TreeHeightCalculator(parents);

		int actual = heightCalculator.computeHeight();

		Assert.assertEquals(4, actual);
	}

	@Test
	public void test3() throws Exception {
		int[] parents = new int[] { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
				18, 19 };
		TreeHeightCalculator heightCalculator = new TreeHeightCalculator(parents);

		int actual = heightCalculator.computeHeight();

		Assert.assertEquals(21, actual);
	}

	@Test
	public void test2() throws Exception {
		int[] parents = new int[] { 4, -1, 4, 1, 1 };
		TreeHeightCalculator heightCalculator = new TreeHeightCalculator(parents);

		int actual = heightCalculator.computeHeight();

		Assert.assertEquals(3, actual);
	}
}
