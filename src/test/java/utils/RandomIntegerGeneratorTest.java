package utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RandomIntegerGeneratorTest {
	private static final int MAX = 100;
	private static final int MIN = 10;
	private static final int ONE = 1;
	private static final int THOUSAND = 100;
	private static final int NEGATIVE_NUMBER = -1;
	private static final int TEN = 10; // example positive number

	@Test
	public void testOneWithMax() throws Exception {
		List<Integer> randomNumbers = new RandomIntegerGenerator(ONE, TEN)
				.toList();

		Assert.assertEquals(ONE, randomNumbers.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeMin() throws Exception {
		new RandomIntegerGenerator(TEN, TEN, NEGATIVE_NUMBER)
				.toList();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeSize() throws Exception {
		new RandomIntegerGenerator(NEGATIVE_NUMBER, TEN, TEN)
				.toList();
	}

	@Test
	public void testThousandWithMaxAndMin() throws Exception {
		List<Integer> randomNumbers = new RandomIntegerGenerator(THOUSAND, MAX, MIN)
				.toList();
		Assert.assertEquals(THOUSAND, randomNumbers.size());
		for (int integer : randomNumbers) {
			Assert.assertTrue("Found value higher than Max", integer < MAX);
			Assert.assertTrue("Found value lower than Min", integer >= MIN);
		}
	}
}
