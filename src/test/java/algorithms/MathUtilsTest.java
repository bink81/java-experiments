package algorithms;

import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {
	@Test
	public void testGreatesCommonDivisorWithSmallNumbers() throws Exception {
		long actual = MathUtils.greatesCommonDivisor(18, 35);

		Assert.assertEquals(1, actual);
	}

	@Test
	public void testGreatesCommonDivisorWithBigNumbers() throws Exception {
		long actual = MathUtils.greatesCommonDivisor(28851538, 1183019);

		Assert.assertEquals(17657, actual);
	}

	@Test
	public void testLeastCommonMultipleWithSmallNumbers() throws Exception {
		long actual = MathUtils.leastCommonMultiple(6, 8);

		Assert.assertEquals(24, actual);
	}

	@Test
	public void testLeastCommonMultipleWithEqualNumbers() throws Exception {
		long actual = MathUtils.leastCommonMultiple(10, 10);

		Assert.assertEquals(10, actual);
	}

	@Test
	public void testLeastCommonMultipleWithBigNumbers() throws Exception {
		long actual = MathUtils.leastCommonMultiple(28851538, 1183019);

		Assert.assertEquals(1933053046, actual);
	}
}
