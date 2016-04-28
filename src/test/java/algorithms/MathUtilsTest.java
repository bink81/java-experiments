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
}
