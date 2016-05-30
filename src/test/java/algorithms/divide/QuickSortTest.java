package algorithms.divide;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;

public class QuickSortTest {

	@Test
	public void testWithEmptyValue() throws Exception {
		int[] a = new int[] {};

		QuickSort.sort(a, 0, a.length - 1);

		int[] expected = new int[] {};
		Assert.assertTrue(Arrays.equals(expected, a));
	}

	@Test
	public void testWithOneValue() throws Exception {
		int[] a = new int[] { 1 };

		QuickSort.sort(a, 0, a.length - 1);

		int[] expected = new int[] { 1 };
		Assert.assertTrue(Arrays.equals(expected, a));
	}

	@Test
	public void testWithTwoOrderedValues() throws Exception {
		int[] a = new int[] { 1, 2 };

		QuickSort.sort(a, 0, a.length - 1);

		int[] expected = new int[] { 1, 2 };
		Assert.assertTrue(Arrays.equals(expected, a));
	}

	@Test
	public void testWithMoreValues() throws Exception {
		int[] a = new int[] { 5, 3, 9, 2, 2 };

		QuickSort.sort(a, 0, a.length - 1);

		int[] expected = new int[] { 2, 2, 3, 5, 9 };
		Assert.assertTrue(Arrays.toString(a), Arrays.equals(expected, a));
	}

	@Test
	public void testAndCompareWithRandomValues() throws Exception {
		int[] a = new RandomIntegerGenerator(1000, Integer.MAX_VALUE).produceArray();

		QuickSort.sort(a, 0, a.length - 1);

		int[] expected = new int[a.length];
		System.arraycopy(a, 0, expected, 0, a.length);
		Arrays.sort(expected);
		Assert.assertTrue(Arrays.toString(a) + "<>" + Arrays.toString(expected),
				Arrays.equals(expected, a));
	}
}
