package financial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wrappers.core.ValueSafe;

/**
 * This class represents money.
 */
@ValueSafe
public final class Money implements Serializable {
	private static final long serialVersionUID = -5210975541585203124L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Money.class);

	public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

	private static final BigDecimal ZERO = BigDecimal.ZERO;

	private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

	private final BigDecimal amount;

	private final Currency currency;

	// constructors
	public Money(String value) {
		this(value, DEFAULT_CURRENCY);
	}

	public Money(String value, Currency currency) {
		this(value != null ? new BigDecimal(value) : null, currency);
	}

	public Money(BigDecimal amount) {
		this(amount, DEFAULT_CURRENCY);
	}

	public Money(BigDecimal amount, Currency currency) {
		if (amount == null) {
			throw new IllegalArgumentException("Amount must not be null!");
		}
		if (currency == null) {
			throw new IllegalArgumentException("Currency must not be null!");
		}
		this.amount = amount;
		this.currency = currency;
	}

	// operations
	public static Money sum(Collection<Money> moneys) {
		Money sum = new Money(ZERO);
		Currency currency = null;
		for (Money money : moneys) {
			checkSameCurrency(currency, money.getCurrency());
			currency = money.getCurrency();
			sum = sum.plus(money);
		}
		return sum;
	}

	public Money plus(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return new Money(getAmount().add(other.getAmount()));
	}

	public Money minus(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return new Money(getAmount().subtract(other.getAmount()));
	}

	public Money divide(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return new Money(getAmount().divide(other.getAmount(), ROUNDING_MODE));
	}

	public Money multiply(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return new Money(getAmount().multiply(other.getAmount()));
	}

	public boolean isPositive() {
		return getAmount().compareTo(ZERO) > 0;
	}

	public boolean isZero() {
		return getAmount().compareTo(ZERO) == 0;
	}

	public boolean isSameCurrency(Money other) {
		return currency.equals(other.currency);
	}

	public boolean equal(Money other) {
		if (!getCurrency().equals(other.getCurrency())) {
			return false;
		}
		return getAmount().compareTo(other.getAmount()) == 0;
	}

	public boolean greaterThan(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return getAmount().compareTo(other.getAmount()) > 0;
	}

	public boolean greaterThanOrEqual(Money other) {
		checkSameCurrency(getCurrency(), other.getCurrency());
		return getAmount().compareTo(other.getAmount()) >= 0;
	}

	public boolean lessThan(Money other) {
		return !greaterThanOrEqual(other);
	}

	public boolean lessThanOrEqual(Money other) {
		return !greaterThan(other);
	}

	private static boolean checkSameCurrency(Currency currency1, Currency currency2) {
		if (currency1 != null && !currenciesAreEqual(currency1, currency2)) {
			throw new IllegalArgumentException(
				"Currency " + currency1 + " is different than " + currency2);
		}
		return true;
	}

	private static boolean currenciesAreEqual(Currency currency1, Currency currency2) {
		return currency2.getCurrencyCode().equals(currency1.getCurrencyCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (!currency.equals(other.currency)) {
			LOGGER.error("Currency {} is different than {}", currency, other.currency);
			return false;
		}
		// if we use standard equals here then we get '1 != 1.00'
		if (amount.compareTo(other.amount) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + ", currency=" + currency + "]";
	}

	// getters
	public final BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}
}
