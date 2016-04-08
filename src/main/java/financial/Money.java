package financial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import wrappers.core.ValueSafe;

/**
 * This class represents money.
 */
@ValueSafe
public final class Money implements Serializable {
	private static final long serialVersionUID = -5210975541585203124L;

	private static final BigDecimal ZERO = BigDecimal.ZERO;

	private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

	private final BigDecimal amount;

	// constructors
	public Money(String value) {
		this(value != null ? new BigDecimal(value) : null);
	}

	public Money(BigDecimal amount) {
		if (amount == null) {
			throw new IllegalArgumentException("Value must not be null!");
		}
		this.amount = amount;
	}

	// getters
	public final BigDecimal getAmount() {
		return amount;
	}

	// operations
	public static Money sum(Collection<Money> moneys) {
		Money sum = new Money(ZERO);
		for (Money money : moneys) {
			sum = sum.plus(money);
		}
		return sum;
	}

	public Money plus(Money other) {
		return new Money(getAmount().add(other.getAmount()));
	}

	public Money minus(Money other) {
		return new Money(getAmount().subtract(other.getAmount()));
	}

	public Money divide(Money other) {
		return new Money(getAmount().divide(other.getAmount(), ROUNDING_MODE));
	}

	public Money multiply(Money other) {
		return new Money(getAmount().multiply(other.getAmount()));
	}

	public boolean isPositive() {
		return getAmount().compareTo(ZERO) > 0;
	}

	public boolean isZero() {
		return getAmount().compareTo(ZERO) == 0;
	}

	public boolean equal(Money other) {
		return getAmount().compareTo(other.getAmount()) == 0;
	}

	public boolean greaterThan(Money other) {
		return getAmount().compareTo(other.getAmount()) > 0;
	}

	public boolean greaterThanOrEqual(Money other) {
		return getAmount().compareTo(other.getAmount()) >= 0;
	}

	public boolean lessThan(Money other) {
		return !greaterThanOrEqual(other);
	}

	public boolean lessThanOrEqual(Money other) {
		return !greaterThan(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		}
		// if we use standard equals here then we get '1 != 1.00'
		else if (amount.compareTo(other.amount) != 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + "]";
	}
}
