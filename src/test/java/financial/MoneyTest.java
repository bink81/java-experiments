package financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;

import org.junit.Test;

import junit.framework.Assert;

public class MoneyTest {
	private static final Currency DKK_CURRENCY = Currency.getInstance("DKK");

	private static final String MULTIPLICATION_FACTOR = "2";

	private static final String VALUE = "10.01";

	private static final String MULTIPLIED_VALUE = "20.02";

	private static final String SMALLER_VALUE = "00.01";

	private static final String NEGATIVE_VALUE = "-10";

	private static final String BIGGER_VALUE = "99999999999999.01";

	private static final String SUMMED_VALUE = "99999999999999.02";

	private static final String POSITIVE_VALUE = "99999999999999.00";

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyValue() throws Exception {
		new Money("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		new Money((String) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullCurrency() throws Exception {
		new Money(VALUE, null);
	}

	@Test
	public void testGetAmount() throws Exception {
		Money money = new Money(VALUE);

		Assert.assertEquals(new BigDecimal(VALUE), money.getAmount());
	}

	@Test
	public void testGetDefaultCurrency() throws Exception {
		Money money = new Money(VALUE);

		Assert.assertEquals(Money.DEFAULT_CURRENCY, money.getCurrency());
	}

	@Test
	public void testGetCurrency() throws Exception {
		Money money = new Money(VALUE, DKK_CURRENCY);

		Assert.assertEquals(DKK_CURRENCY, money.getCurrency());
	}

	@Test
	public void testEquals() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE);

		Assert.assertEquals(money1, money2);
	}

	@Test
	public void testEqual() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE);

		Assert.assertTrue(money1.equal(money2));
	}

	@Test
	public void testEqualForDifferentCurrencies() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE, DKK_CURRENCY);

		Assert.assertFalse(money1.equal(money2));
	}

	@Test
	public void testTrueGreaterThanOrEqual() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE);

		Assert.assertTrue(money1.greaterThanOrEqual(money2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTrueGreaterThanOrEqualForDifferentCurrencies() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE, DKK_CURRENCY);

		money1.greaterThanOrEqual(money2);
	}

	@Test
	public void testTrueLessThanOrEqual() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE);

		Assert.assertTrue(money1.lessThanOrEqual(money2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTrueLessThanOrEqualForDifferentCurrencies() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE, DKK_CURRENCY);

		money1.lessThanOrEqual(money2);
	}

	@Test
	public void testFalseGreaterThan() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE);

		Assert.assertFalse(money1.greaterThan(money2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFalseGreaterThanForDifferentCurrencies() throws Exception {
		Money money1 = new Money(VALUE);
		Money money2 = new Money(VALUE, DKK_CURRENCY);

		money1.greaterThan(money2);
	}

	@Test
	public void testTrueGreaterThan() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE);

		Assert.assertTrue(money1.greaterThan(money2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTrueGreaterThanForDifferentCurrencies() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE, DKK_CURRENCY);

		money1.greaterThan(money2);
	}

	@Test
	public void testIsZero() throws Exception {
		Money money = new Money("0");

		Assert.assertTrue(money.isZero());
	}

	@Test
	public void testIsPositive() throws Exception {
		Money money = new Money(SMALLER_VALUE);

		Assert.assertTrue(money.isPositive());
	}

	@Test
	public void testIsSameCurrency() throws Exception {
		Money money1 = new Money(SMALLER_VALUE);
		Money money2 = new Money(BIGGER_VALUE);

		Assert.assertTrue(money1.isSameCurrency(money2));
	}

	@Test
	public void testIsSameCurrencyForDifferentCurrencies() throws Exception {
		Money money1 = new Money(SMALLER_VALUE);
		Money money2 = new Money(BIGGER_VALUE, DKK_CURRENCY);

		Assert.assertFalse(money1.isSameCurrency(money2));
	}

	@Test
	public void testTrueLessThan() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE);

		Assert.assertTrue(money2.lessThan(money1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTrueLessThanForDifferentCurrencies() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE, DKK_CURRENCY);

		money2.lessThan(money1);
	}

	@Test
	public void testSumCollection() throws Exception {
		Collection<Money> moneys = new ArrayList<>();
		moneys.add(new Money(SMALLER_VALUE));
		moneys.add(new Money(BIGGER_VALUE));

		Money sum = Money.sum(moneys);

		Assert.assertTrue(new Money(SUMMED_VALUE).equal(sum));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSumCollectionForDifferentCurrencies() throws Exception {
		Collection<Money> moneys = new ArrayList<>();
		moneys.add(new Money(SMALLER_VALUE));
		moneys.add(new Money(BIGGER_VALUE, DKK_CURRENCY));

		Money.sum(moneys);
	}

	@Test
	public void testPlus() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE);

		Money sum = money2.plus(money1);

		Assert.assertEquals(new Money(SUMMED_VALUE), sum);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlusForDifferentCurrencies() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE, DKK_CURRENCY);

		money2.plus(money1);
	}

	@Test
	public void testMinus() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE);

		Money minus = money1.minus(money2);

		Assert.assertEquals(new Money(POSITIVE_VALUE), minus);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMinusForDifferentCurrencies() throws Exception {
		Money money1 = new Money(BIGGER_VALUE);
		Money money2 = new Money(SMALLER_VALUE, DKK_CURRENCY);

		money1.minus(money2);
	}

	@Test
	public void testNegativeMinus() throws Exception {
		Money money1 = new Money(SMALLER_VALUE);
		Money money2 = new Money(VALUE);

		Money minus = money1.minus(money2);

		Assert.assertEquals(new Money(NEGATIVE_VALUE), minus);
	}

	@Test
	public void testMultiply() throws Exception {
		Money money1 = new Money(MULTIPLICATION_FACTOR);
		Money money2 = new Money(VALUE);

		Money minus = money2.multiply(money1);

		Assert.assertEquals(new Money(MULTIPLIED_VALUE), minus);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultiplyForDifferentCurrencies() throws Exception {
		Money money1 = new Money(MULTIPLICATION_FACTOR);
		Money money2 = new Money(VALUE, DKK_CURRENCY);

		money2.multiply(money1);
	}
}
