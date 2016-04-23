package utils;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class RandomArrayGeneratorTest {
	private static final int ONE = 1;

	@Test
	public void testName() throws Exception {
		List<Integer> randomNumbers = new RandomArrayGenerator(ONE, 10).generateRandomNumbers();

		Assert.assertEquals(ONE, randomNumbers.size());
	}
}
