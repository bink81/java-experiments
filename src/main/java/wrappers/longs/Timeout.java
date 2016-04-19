package wrappers.longs;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.LongWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents a Timeout (positive long).
 */
@ValueSafe
public final class Timeout extends LongWrapper {

	private static final long serialVersionUID = -2699024192078008588L;

	private Timeout(Long value) {
		super(value);
		checkArgument(value > 0, "The timeout must be positive:  %s", value);
	}

	public static Timeout of(Long value) {
		return new Timeout(value);
	}
}
