package wrappers.longs;

import org.junit.Test;

import junit.framework.Assert;

public class TimeoutTest {
	private static final Long ONE = 1L;

	private static final Long MINUS_ONE = -1L;

	private static final Long ZERO = 0L;

	@Test
	public void testPositiveValue() throws Exception {
		Timeout positiveLong = Timeout.of(ONE);

		Assert.assertEquals(ONE, positiveLong.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZeroValue() throws Exception {
		Timeout.of(ZERO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeValue() throws Exception {
		Timeout.of(MINUS_ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Timeout.of(null);
	}
}
