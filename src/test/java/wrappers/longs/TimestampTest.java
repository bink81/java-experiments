package wrappers.longs;

import org.junit.Test;

import junit.framework.Assert;

public class TimestampTest {
	private static final Long ONE = 1L;

	private static final Long MINUS_ONE = -1L;

	private static final Long ZERO = 0L;

	@Test
	public void testPositiveValue() throws Exception {
		Timestamp positiveLong = Timestamp.of(ONE);

		Assert.assertEquals(ONE, positiveLong.get());
	}

	@Test
	public void testZeroValue() throws Exception {
		Timestamp positiveLong = Timestamp.of(ZERO);

		Assert.assertEquals(ZERO, positiveLong.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeValue() throws Exception {
		Timestamp.of(MINUS_ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Timestamp.of(null);
	}
}
