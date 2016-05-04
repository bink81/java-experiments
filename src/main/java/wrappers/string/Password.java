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

	private static final int PARAMETER_MAX_SIZE = 128;

	private Password(String value) {
		super(value);
		checkArgument(
			value.length() <= PARAMETER_MAX_SIZE,
			"The length of %s exceeds maximum number of characters must be %s", value, PARAMETER_MAX_SIZE);
		checkArgument(
			new PasswordLengthValidator().isValid(value), "Not enough characters in password: %s",
			value.length() + ". It must be from " + PasswordLengthValidator.MINIMUM_LENGTH + " to "
					+ PasswordLengthValidator.MAXIMUM_LENGTH);
	}

	public static Password of(String value) {
		return new Password(value);
	}
}
