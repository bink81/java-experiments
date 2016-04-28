package algorithms;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {
	@Test
	public void testGreatestCommonDivisorWithZeroAsB() throws Exception {
		int randomInteger = fetchRandomInteger();
		long actual = MathUtils.greatestCommonDivisor(randomInteger, IntegerContansts.ZERO);

		Assert.assertEquals(randomInteger, actual);
	}

	@Test
	public void testGreatestCommonDivisorWithZeroAsA() throws Exception {
		int randomInteger = fetchRandomInteger();
		long actual = MathUtils.greatestCommonDivisor(IntegerContansts.ZERO, randomInteger);

		Assert.assertEquals(randomInteger, actual);
	}

	@Test
	public void testGreatestCommonDivisorWithSmallNumbers() throws Exception {
		long actual = MathUtils.greatestCommonDivisor(18, 35);

		Assert.assertEquals(IntegerContansts.ONE, actual);
	}

	@Test
	public void testGreatestCommonDivisorWithBigNumbers() throws Exception {
		long actual = MathUtils.greatestCommonDivisor(28851538, 1183019);

		Assert.assertEquals(17657, actual);
	}

	@Test
	public void testLeastCommonMultipleWithZeroAsB() throws Exception {
		int randomInteger = fetchRandomInteger();
		long actual = MathUtils.leastCommonMultiple(randomInteger, IntegerContansts.ZERO);

		Assert.assertEquals(IntegerContansts.ZERO, actual);
	}

	@Test
	public void testLeastCommonMultipleWithZeroAsA() throws Exception {
		int randomInteger = fetchRandomInteger();
		long actual = MathUtils.leastCommonMultiple(IntegerContansts.ZERO, randomInteger);

		Assert.assertEquals(IntegerContansts.ZERO, actual);
	}

	private int fetchRandomInteger() {
		return new Random().nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void testLeastCommonMultipleWithSmallNumbers() throws Exception {
		long actual = MathUtils.leastCommonMultiple(6, 8);

		Assert.assertEquals(24, actual);
	}

	@Test
	public void testLeastCommonMultipleWithEqualNumbers() throws Exception {
		int randomInteger = fetchRandomInteger();
		long actual = MathUtils.leastCommonMultiple(randomInteger, randomInteger);

		Assert.assertEquals(randomInteger, actual);
	}

	@Test
	public void testLeastCommonMultipleWithBigNumbers() throws Exception {
		long actual = MathUtils.leastCommonMultiple(28851538, 1183019);

		Assert.assertEquals(1933053046, actual);
	}

	@Test
	public void testMinimumDotProductWithZeros() throws Exception {
		int[] a = new int[] {};
		int[] b = new int[] {};
		long actual = MathUtils.minimumDotProduct(a, b);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testMinimumDotProductWithOneSolution() throws Exception {
		int[] a = new int[] { 23 };
		int[] b = new int[] { 39 };
		long actual = MathUtils.minimumDotProduct(a, b);

		Assert.assertEquals(897, actual);
	}

	@Test
	public void testMinimumDotProductWithWithPermutations() throws Exception {
		int[] a = new int[] { 1, 3, -5 };
		int[] b = new int[] { -2, 4, 1 };
		long actual = MathUtils.minimumDotProduct(a, b);

		Assert.assertEquals(-25, actual);
	}

	@Test
	public void testMinimumDotProductWith12345() throws Exception {
		int[] a = new int[] { 1, 2, 3, 4, 5 };
		int[] b = new int[] { 1, 0, 1, 0, 1 };
		long actual = MathUtils.minimumDotProduct(a, b);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testMinimumDotProductWithIntOverflow() throws Exception {
		int[] a = new int[] { 999999999 };
		int[] b = new int[] { 999999999 };
		long actual = MathUtils.minimumDotProduct(a, b);

		Assert.assertEquals(999999998000000001L, actual);
	}
}
