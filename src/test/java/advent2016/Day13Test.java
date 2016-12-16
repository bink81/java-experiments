package advent2016;

import org.junit.Assert;
import org.junit.Test;

public class Day13Test {

	private static final int FAVORITE_NUMBER = 1364;

	private static final int TEST_NUMBER = 10;

	private static final int IRRELEVANT_VALUE = 1000;

	@Test
	public void testCalculateStepsTest() throws Exception {
		long actual = new Day13(TEST_NUMBER, 7, 4).calculateSteps();

		Assert.assertEquals(11, actual);
	}

	@Test
	public void testCalculateSteps() throws Exception {
		long actual = new Day13(FAVORITE_NUMBER, 31, 39).calculateSteps();

		Assert.assertEquals(86, actual);
	}

	@Test
	public void testDisctinctReachableStepsTest() throws Exception {
		long actual = new Day13(TEST_NUMBER, IRRELEVANT_VALUE, IRRELEVANT_VALUE)
			.calculateDisctinctReachableSteps(5);

		Assert.assertEquals(10, actual);
	}

	@Test
	public void testDisctinctReachableSteps() throws Exception {
		long actual = new Day13(FAVORITE_NUMBER, IRRELEVANT_VALUE, IRRELEVANT_VALUE)
			.calculateDisctinctReachableSteps(50);

		Assert.assertEquals(127, actual);
	}
}
