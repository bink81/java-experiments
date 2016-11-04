package probability;

import org.junit.Assert;
import org.junit.Test;

public class CoinTosserTest {

	@Test
	public void testCalculateProbabilityForOneToss() throws Exception {
		double p = new CoinTosser(1, 1).calculateProbability();

		Assert.assertEquals(0.5, p, 0.01);
	}

	@Test
	public void testCalculateProbabilityForTwoToss() throws Exception {
		double p = new CoinTosser(8, 8).calculateProbability();

		Assert.assertEquals(0.0039, p, 0.01);
	}

	@Test
	public void testCalculateProbabilityForZeroToss() throws Exception {
		double p = new CoinTosser(0, 1).calculateProbability();

		Assert.assertEquals(0.0, p, 0.0);
	}

	@Test
	public void testCalculateProbabilityForNoHeads() throws Exception {
		double p = new CoinTosser(1, 0).calculateProbability();

		// You don't have to toss even once to get 100%
		Assert.assertEquals(1.0, p, 0.0);
	}
}
