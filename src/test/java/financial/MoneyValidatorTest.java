package financial;

import org.junit.Test;

import financial.MoneyValidator;
import org.junit.Assert;
import wrappers.core.WrapperValidator;

public class MoneyValidatorTest {

	private WrapperValidator moneyValidator = new MoneyValidator();

	@Test
	public void testEmpty() throws Exception {
		Assert.assertFalse(moneyValidator.isValid(""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNull() throws Exception {
		Assert.assertTrue(moneyValidator.isValid(null));
	}

	@Test
	public void testZero() throws Exception {
		Assert.assertTrue(moneyValidator.isValid("0"));
	}

	@Test
	public void testPositive() throws Exception {
		Assert.assertTrue(moneyValidator.isValid("1"));
		Assert.assertTrue(moneyValidator.isValid("1000"));
		Assert.assertTrue(moneyValidator.isValid("1000000000000"));
	}

	@Test
	public void testLessThanOne() throws Exception {
		Assert.assertTrue(moneyValidator.isValid("0.333333333333"));
	}

	@Test
	public void testNegative() throws Exception {
		Assert.assertFalse(moneyValidator.isValid("-1"));
	}

	@Test
	public void testCharacters() throws Exception {
		Assert.assertFalse(moneyValidator.isValid("AAA"));
	}
}
