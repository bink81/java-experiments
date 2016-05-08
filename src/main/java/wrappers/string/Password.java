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
	private static final String VALIDATION_ERROR = "Password format not valid. ";

	private static final long serialVersionUID = 1664428264118410830L;

	public static final int MINIMUM_LENGTH = 6;

	public static final int MAXIMUM_LENGTH = 20;

	private Password(String value) {
		super(value);
		checkArgument(
			value.length() <= MAXIMUM_LENGTH,
			"The length of %s exceeds maximum number of characters must be %s", value,
			MAXIMUM_LENGTH);
		checkArgument(
			new RegexValidator("^(.+){" + MINIMUM_LENGTH + "," + MAXIMUM_LENGTH + "}$")
				.isValid(value),
			VALIDATION_ERROR + "It must be from " + MINIMUM_LENGTH + " to " + MAXIMUM_LENGTH);
		checkArgument(
			new RegexValidator(".*[a-z]").isValid(value),
			VALIDATION_ERROR + "It must have at least one lower case character.");
		checkArgument(
			new RegexValidator("^(.*?[A-Z]){3,}.*$").isValid(value),
			VALIDATION_ERROR + "It must have at least three upper case characters.");
	}

	public static Password of(String value) {
		return new Password(value);
	}
}
