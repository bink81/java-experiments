package utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RandomArrayGeneratorTest {
	private static final int MAX = 100;
	private static final int MIN = 10;
	private static final int ONE = 1;
	private static final int THOUSAND = 100;
	private static final int NEGATIVE_NUMBER = -1;
	private static final int TEN = 10; // example positive number

	@Test
	public void testOneWithMax() throws Exception {
		List<Integer> randomNumbers = new RandomArrayGenerator(ONE, TEN)
				.generateRandomNumbers();

		Assert.assertEquals(ONE, randomNumbers.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeMin() throws Exception {
		new RandomArrayGenerator(TEN, TEN, NEGATIVE_NUMBER)
				.generateRandomNumbers();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeSize() throws Exception {
		new RandomArrayGenerator(NEGATIVE_NUMBER, TEN, TEN)
				.generateRandomNumbers();
	}

	@Test
	public void testThousandWithMaxAndMin() throws Exception {
		List<Integer> randomNumbers = new RandomArrayGenerator(THOUSAND, MAX, MIN)
				.generateRandomNumbers();
		Assert.assertEquals(THOUSAND, randomNumbers.size());
		for (int integer : randomNumbers) {
			Assert.assertTrue("Found value higher than Max", integer < MAX);
			Assert.assertTrue("Found value lower than Min", integer >= MIN);
		}
	}
}
