package wrappers.string;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.SecretStringWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents a password that must consist of at least one character.
 * It should not expose the wrapped value in a clear text, by accident, in logs
 */
@ValueSafe
public final class Password extends SecretStringWrapper {
	private static final long serialVersionUID = 1664428264118410830L;

	private static final int MAX_SIZE = 128;

	private Password(String value) {
		super(value);
		checkArgument(
			new PasswordValidator().isValid(value), "No characters detected in string  '%s'",
			value);
		checkArgument(
			value.length() <= MAX_SIZE,
			"The length of %s exceeds maximum number of characters must be %s", value, MAX_SIZE);
	}

	public static Password of(String value) {
		return new Password(value);
	}
}
