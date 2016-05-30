package algorithms;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;

public class PolynomialUtilsTest {
	@Test(expected = IllegalArgumentException.class)
	public void testMultiplyWithANull() throws Exception {
		int[] a = null;
		int[] b = new int[] { 1 };

		PolynomialUtils.multiply(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultiplyWithBNull() throws Exception {
		int[] a = new int[] { 1 };
		int[] b = null;

		PolynomialUtils.multiply(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultiplyWithDifferentSizes() throws Exception {
		int[] a = new int[] { 1 };
		int[] b = new int[] { 1, 2 };

		PolynomialUtils.multiply(a, b);
	}

	@Test
	public void testMultiplyWithEmptySizes() throws Exception {
		int[] a = new int[] {};
		int[] b = new int[] {};

		long[] actual = PolynomialUtils.multiply(a, b);

		Assert.assertTrue(Arrays.equals(new long[] { 0 }, actual));
	}

	@Test
	public void testMultiplyWithZeroMatrix() throws Exception {
		int[] a = new int[] { 1 };
		int[] b = new int[] { 0 };

		long[] actual = PolynomialUtils.multiply(a, b);

		Assert.assertTrue(Arrays.equals(new long[] { 0 }, actual));
	}

	@Test
	public void testMultiply1() throws Exception {
		int[] a = new int[] { 3, 2, 5 };
		int[] b = new int[] { 5, 1, 2 };

		long[] actual = PolynomialUtils.multiply(a, b);

		long[] expected = new long[] { 15L, 13L, 33L, 9L, 10L };
		Assert.assertTrue(Arrays.equals(expected, actual));
	}

	@Test
	public void testMultiply2() throws Exception {
		int[] a = new int[] { 4, 3, 2, 1 };
		int[] b = new int[] { 1, 2, 3, 4 };

		long[] actual = PolynomialUtils.multiply(a, b);

		long[] expected = new long[] { 4L, 11L, 20L, 30L, 20L, 11L, 4L };
		Assert.assertTrue(Arrays.equals(expected, actual));
	}

	@Test
	public void testMultiplyMaxIntegers() throws Exception {
		int[] a = new int[] { Integer.MAX_VALUE };
		int[] b = new int[] { Integer.MAX_VALUE };

		long[] actual = PolynomialUtils.multiply(a, b);

		long[] expected = new long[] { 4611686014132420609L };
		Assert.assertTrue(Arrays.toString(actual), Arrays.equals(expected, actual));
	}

	@Test
	public void testMultiplyWithRandomMatrix() throws Exception {
		int[] a = new RandomIntegerGenerator(100, Integer.MAX_VALUE).produceArray();
		int[] b = new RandomIntegerGenerator(100, Integer.MAX_VALUE).produceArray();

		PolynomialUtils.multiply(a, b);
	}
}
