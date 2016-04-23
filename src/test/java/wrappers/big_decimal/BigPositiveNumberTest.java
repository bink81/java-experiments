package wrappers.big_decimal;

import java.math.BigDecimal;

import org.junit.Test;

import org.junit.Assert;

public class BigPositiveNumberTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		BigPositiveNumber.of(null);
	}

	@Test
	public void testPositiveValue() throws Exception {
		BigPositiveNumber positiveInteger = BigPositiveNumber.of(BigDecimal.ONE);

		Assert.assertEquals(BigDecimal.ONE, positiveInteger.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZeroValue() throws Exception {
		BigPositiveNumber positiveInteger = BigPositiveNumber.of(BigDecimal.ZERO);

		Assert.assertEquals(BigDecimal.ZERO, positiveInteger.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeValue() throws Exception {
		BigPositiveNumber.of(new BigDecimal("-1"));
	}
}
