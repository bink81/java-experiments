package algorithms.greedy;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import algorithms.greedy.MoneyChangeCalculator;
import utils.RandomIntegerGenerator;

public class MoneyChangeCalculatorTest {

	@Test
	public void testZero() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(0);

		Assert.assertEquals(0, actual);
	}

	@Test
	public void testTwo() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(2);

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testBigger() throws Exception {
		int actual = MoneyChangeCalculator.calculateMinimumChange(28);

		Assert.assertEquals(6, actual);
	}

	@Test
	public void testRandomArray() throws Exception {
		List<Integer> randomAmounts =
				new RandomIntegerGenerator(100, 999999999).toList();
		for (Integer randomAmount : randomAmounts) {
			MoneyChangeCalculator.calculateMinimumChange(randomAmount);
		}
	}
}
