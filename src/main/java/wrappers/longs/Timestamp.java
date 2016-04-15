package wrappers.longs;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.LongWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents a Timestamp (non negative long).
 */
@ValueSafe
public final class Timestamp extends LongWrapper {

	private static final long serialVersionUID = -6860516853206626352L;

	private Timestamp(Long value) {
		super(value);
		checkArgument(value >= 0, "The timestamp must not be negative:  %s", value);
	}

	public static Timestamp of(Long value) {
		return new Timestamp(value);
	}
}
