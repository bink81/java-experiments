package algorithms;

import org.junit.Assert;
import org.junit.Test;

public class MinimumDotProductTest {
	@Test
	public void testZeros() throws Exception {
		int[] a = new int[] {};
		int[] b = new int[] {};
		long actual = MinimumDotProduct.calculate(a, b);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testOneSolution() throws Exception {
		int[] a = new int[] { 23 };
		int[] b = new int[] { 39 };
		long actual = MinimumDotProduct.calculate(a, b);

		Assert.assertEquals(897, actual);
	}

	@Test
	public void testWithPermutations() throws Exception {
		int[] a = new int[] { 1, 3, -5 };
		int[] b = new int[] { -2, 4, 1 };
		long actual = MinimumDotProduct.calculate(a, b);

		Assert.assertEquals(-25, actual);
	}

	@Test
	public void test12345() throws Exception {
		int[] a = new int[] { 1, 2, 3, 4, 5 };
		int[] b = new int[] { 1, 0, 1, 0, 1 };
		long actual = MinimumDotProduct.calculate(a, b);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testIntOverflow() throws Exception {
		int[] a = new int[] { 999999999 };
		int[] b = new int[] { 999999999 };
		long actual = MinimumDotProduct.calculate(a, b);

		Assert.assertEquals(999999998000000001L, actual);
	}
}
