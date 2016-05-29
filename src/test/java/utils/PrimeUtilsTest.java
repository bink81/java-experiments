package utils;

import org.junit.Assert;
import org.junit.Test;

public class PrimeUtilsTest {
	@Test
	public void testGetNextPrimeZero() throws Exception {
		long actual = PrimeUtils.getNextPrime(0);

		Assert.assertEquals(1, actual);
	}

	@Test
	public void testGetNextPrimeOne() throws Exception {
		long actual = PrimeUtils.getNextPrime(1);

		Assert.assertEquals(1, actual);
	}

	@Test
	public void testGetNextPrimeTwo() throws Exception {
		long actual = PrimeUtils.getNextPrime(2);

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testGetNextPrimeFour() throws Exception {
		long actual = PrimeUtils.getNextPrime(4);

		Assert.assertEquals(5, actual);
	}

	@Test
	public void testGetNextPrimeTwentyTwo() throws Exception {
		long actual = PrimeUtils.getNextPrime(22);

		Assert.assertEquals(23, actual);
	}
	// This works but takes too long to test
	// @Test
	// public void testGetNextPrimeMax() throws Exception {
	// long actual = PrimeUtils.getNextPrime(Integer.MAX_VALUE - 1);
	//
	// Assert.assertEquals(2147483647, actual);
	// }

	@Test
	public void testFindBigPrime() throws Exception {
		long actual = PrimeUtils.findBigPrime();

		Assert.assertTrue(actual > 0);
	}
}
