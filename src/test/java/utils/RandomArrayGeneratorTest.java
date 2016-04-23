package utils;

import java.util.List;

import org.junit.Test;

import org.junit.Assert;

public class RandomArrayGeneratorTest {
	private static final int MAX = 100;
	private static final int MIN = 10;
	private static final int ONE = 1;
	private static final int THOUSAND = 100;

	@Test
	public void testOneWithMax() throws Exception {
		List<Integer> randomNumbers = new RandomArrayGenerator(ONE, 10).generateRandomNumbers();

		Assert.assertEquals(ONE, randomNumbers.size());
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
