package wrappers.string;

import wrappers.core.SecretStringWrapper;
import wrappers.core.ValueSafe;

/**
 * Prevents exposing a password in a clear text, by accident, in logs
 */
@ValueSafe
public final class Password extends SecretStringWrapper {
	private static final long serialVersionUID = 1664428264118410830L;

	private Password(String value) {
		super(value);
	}

	// constructors
	public static Password of(String value) {
		return new Password(value);
	}
}
