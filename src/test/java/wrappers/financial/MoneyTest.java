package wrappers.financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import junit.framework.Assert;

public class MoneyTest {
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
		Money.of("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Money.of(null);
	}

	@Test
	public void testToString() throws Exception {
		Money money = Money.of(VALUE);

		Assert.assertEquals(new BigDecimal(VALUE), money.getAmount());
	}

	@Test
	public void testEquals() throws Exception {
		Money money1 = Money.of(VALUE);
		Money money2 = Money.of(VALUE);

		Assert.assertEquals(money1, money2);
	}

	@Test
	public void testEqual() throws Exception {
		Money money1 = Money.of(VALUE);
		Money money2 = Money.of(VALUE);

		Assert.assertTrue(money1.equal(money2));
	}

	@Test
	public void testTrueGreaterThanOrEqual() throws Exception {
		Money money1 = Money.of(VALUE);
		Money money2 = Money.of(VALUE);

		Assert.assertTrue(money1.greaterThanOrEqual(money2));
	}

	@Test
	public void testTrueLessThanOrEqual() throws Exception {
		Money money1 = Money.of(VALUE);
		Money money2 = Money.of(VALUE);

		Assert.assertTrue(money1.lessThanOrEqual(money2));
	}

	@Test
	public void testFalseGreaterThan() throws Exception {
		Money money1 = Money.of(VALUE);
		Money money2 = Money.of(VALUE);

		Assert.assertFalse(money1.greaterThan(money2));
	}

	@Test
	public void testTrueGreaterThan() throws Exception {
		Money money1 = Money.of(BIGGER_VALUE);
		Money money2 = Money.of(SMALLER_VALUE);

		Assert.assertTrue(money1.greaterThan(money2));
	}

	@Test
	public void testIsZero() throws Exception {
		Money money = Money.of("0");

		Assert.assertTrue(money.isZero());
	}

	@Test
	public void testIsPositive() throws Exception {
		Money money = Money.of(SMALLER_VALUE);

		Assert.assertTrue(money.isPositive());
	}

	@Test
	public void testTrueLessThan() throws Exception {
		Money money1 = Money.of(BIGGER_VALUE);
		Money money2 = Money.of(SMALLER_VALUE);

		Assert.assertTrue(money2.lessThan(money1));
	}

	@Test
	public void testSumCollection() throws Exception {
		Collection<Money> moneys = new ArrayList<>();
		moneys.add(Money.of(SMALLER_VALUE));
		moneys.add(Money.of(BIGGER_VALUE));

		Money sum = Money.sum(moneys);

		Assert.assertTrue(Money.of(SUMMED_VALUE).equal(sum));
	}

	@Test
	public void testPlus() throws Exception {
		Money money1 = Money.of(BIGGER_VALUE);
		Money money2 = Money.of(SMALLER_VALUE);

		Money sum = money2.plus(money1);

		Assert.assertEquals(Money.of(SUMMED_VALUE), sum);
	}

	@Test
	public void testXor() throws Exception {
		Assert.assertTrue(true);
		;
	}

	@Test
	public void testMinus() throws Exception {
		Money money1 = Money.of(BIGGER_VALUE);
		Money money2 = Money.of(SMALLER_VALUE);

		Money minus = money1.minus(money2);

		Assert.assertEquals(Money.of(POSITIVE_VALUE), minus);
	}

	@Test
	public void testNegativeMinus() throws Exception {
		Money money1 = Money.of(SMALLER_VALUE);
		Money money2 = Money.of(VALUE);

		Money minus = money1.minus(money2);

		Assert.assertEquals(Money.of(NEGATIVE_VALUE), minus);
	}

	@Test
	public void testMultiply() throws Exception {
		Money money1 = Money.of(MULTIPLICATION_FACTOR);
		Money money2 = Money.of(VALUE);

		Money minus = money2.multiply(money1);

		Assert.assertEquals(Money.of(MULTIPLIED_VALUE), minus);
	}
}
