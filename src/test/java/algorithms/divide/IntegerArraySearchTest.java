package algorithms.divide;

import org.junit.Assert;
import org.junit.Test;

public class IntegerArraySearchTest {

	@Test
	public void testZeroPositiveValue() throws Exception {
		int[] a = new int[] {};

		int actual = IntegerArraySearch.binarySearch(a, 1);

		Assert.assertEquals(IntegerArraySearch.RESULT_NOT_FOUND, actual);
	}

	@Test
	public void testOnePositiveValueOK() throws Exception {
		int[] a = new int[] { 7 };

		int actual = IntegerArraySearch.binarySearch(a, 7);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testOnePositiveValueNOK() throws Exception {
		int[] a = new int[] { 7 };

		int actual = IntegerArraySearch.binarySearch(a, 2);

		Assert.assertEquals(IntegerArraySearch.RESULT_NOT_FOUND, actual);
	}

	@Test
	public void testFewPositiveValues() throws Exception {
		int[] a = new int[] { 1, 6, 7 };
		int expectedIndex = 2;

		int actual = IntegerArraySearch.binarySearch(a, a[expectedIndex]);

		Assert.assertEquals(expectedIndex, actual);
	}

	@Test
	public void testMorePositiveValues() throws Exception {
		int[] a = new int[] { 1, 5, 8, 12, 13 };
		int[] expectedIndexes = new int[] { 2, 0, -1, 0, -1 };
		int[] numbers = new int[] { 8, 1, 23, 1, 11 };

		for (int i = 0; i < numbers.length; i++) {
			int actual = IntegerArraySearch.binarySearch(a, numbers[i]);
			Assert.assertEquals(expectedIndexes[i], actual);
		}
	}
}
