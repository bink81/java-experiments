package wrappers.booleans;

import org.junit.Test;

import junit.framework.Assert;

public class IsOptionalTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		IsOptional.of(null);
	}

	@Test
	public void testZeroValue() throws Exception {
		IsOptional isOptionalFalse = IsOptional.of(false);

		Assert.assertFalse(isOptionalFalse.get());
	}

	@Test
	public void testNegativeValue() throws Exception {
		IsOptional isOptionalTrue = IsOptional.of(true);

		Assert.assertTrue(isOptionalTrue.get());
	}
}
