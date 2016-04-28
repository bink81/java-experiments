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
}
