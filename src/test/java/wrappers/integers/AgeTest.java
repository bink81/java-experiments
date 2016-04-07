package wrappers.integers;

import org.junit.Test;

import junit.framework.Assert;

public class AgeTest {
	private static final Integer ONE = 1;

	private static final Integer ZERO = 0;

	@Test
	public void testPositiveValue() throws Exception {
		Age positiveInteger = Age.of(ONE);

		Assert.assertEquals(ONE, positiveInteger.get());
	}

	@Test
	public void testZeroValue() throws Exception {
		Age positiveInteger = Age.of(ZERO);

		Assert.assertEquals(ZERO, positiveInteger.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeValue() throws Exception {
		Age.of(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Age.of(null);
	}
}
