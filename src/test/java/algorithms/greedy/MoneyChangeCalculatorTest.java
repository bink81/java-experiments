package algorithms.greedy;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;

public class MoneyChangeCalculatorTest {

	@Test
	public void testZero() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(0, new int[] { 10, 5, 1 });

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testTwo() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(2, new int[] { 10, 1, 5 });

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testBigger() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(28, new int[] { 1, 5, 10 });

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testRandomArray() throws Exception {
		int[] coinTypes = new int[] { 10, 5, 1 };
		List<Integer> randomAmounts = new RandomIntegerGenerator(1, Integer.MAX_VALUE).produceList();
		for (Integer randomAmount : randomAmounts) {
			// We just check that it doesn't crash
			MoneyChangeCalculator.calculateMinimumChange(randomAmount, coinTypes);
		}
	}

	@Test
	public void test32() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(32, new int[] { 20, 8, 1 });

		Assert.assertEquals(6, actual);
	}

	@Test
	public void test24() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(32, new int[] { 20, 8, 1 });

		Assert.assertEquals(6, actual);
	}
}
