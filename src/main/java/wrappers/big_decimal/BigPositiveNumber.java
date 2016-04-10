package wrappers.big_decimal;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigDecimal;

import wrappers.core.BigDecimalWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents an age (non negative integer).
 */
@ValueSafe
public final class BigPositiveNumber extends BigDecimalWrapper {
	private static final long serialVersionUID = -5210975541585203124L;

	private BigPositiveNumber(BigDecimal value) {
		super(value);
		checkArgument(value.compareTo(BigDecimal.ZERO) > 0, "The value must be positive:  %s", value);
	}

	public static BigPositiveNumber of(BigDecimal value) {
		return new BigPositiveNumber(value);
	}
}
