package algorithms;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciVariationsTest {

	@Test
	public void testResultForZero() throws Exception {
		long actual = FibonacciVariations.calculateResult(IntegerContansts.ZERO);

		Assert.assertEquals(IntegerContansts.ZERO, actual);
	}

	@Test
	public void testResultForOne() throws Exception {
		long actual = FibonacciVariations.calculateResult(1);

		Assert.assertEquals(IntegerContansts.ONE, actual);
	}

	@Test
	public void testResultForTwo() throws Exception {
		long actual = FibonacciVariations.calculateResult(2);

		Assert.assertEquals(IntegerContansts.ONE, actual);
	}

	@Test
	public void testResultForCThree() throws Exception {
		long actual = FibonacciVariations.calculateResult(3);

		Assert.assertEquals(IntegerContansts.TWO, actual);
	}

	@Test
	public void testResultForTen() throws Exception {
		long actual = FibonacciVariations.calculateResult(IntegerContansts.TEN);

		Assert.assertEquals(55, actual);
	}

	@Test
	public void testResultForMax() throws Exception {
		long actual = FibonacciVariations.calculateResult(45);

		Assert.assertEquals(1134903170, actual);
	}

	@Test
	public void testLastDigitForThree() throws Exception {
		long actual = FibonacciVariations.calculateLastDigit(3);

		Assert.assertEquals(IntegerContansts.TWO, actual);
	}

	@Test
	public void testLastDigitForBigNumber() throws Exception {
		long actual = FibonacciVariations.calculateLastDigit(327305);

		Assert.assertEquals(IntegerContansts.FIVE, actual);
	}
}
