package wrappers.integers;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.IntegerWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents an age (non negative integer).
 */
@ValueSafe
public final class Age extends IntegerWrapper {
	private static final long serialVersionUID = -5210975541585203124L;

	private Age(Integer value) {
		super(value);
		checkArgument(value >= 0, "The age must not be negative:  %s", value);
	}

	public static Age of(Integer value) {
		return new Age(value);
	}
}
