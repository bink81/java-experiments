package wrappers.string;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.StringWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents a first name that must consist of at least one characters.
 */
@ValueSafe
public final class FirstName extends StringWrapper {
	private static final long serialVersionUID = 1709313064609717940L;

	private static final int MIN_NUMBER_OF_LETTERS = 1;

	private static final int MAX_NUMBER_OF_LETTERS = 100;

	private FirstName(String value) {
		super(value);
		checkArgument(
			new NameValidator().isValid(value), "Non-letters detected in string  %s", value);
		checkArgument(
			value.length() >= MIN_NUMBER_OF_LETTERS, "The minimum number of letters must be %s",
			MIN_NUMBER_OF_LETTERS);
		checkArgument(
			value.length() <= MAX_NUMBER_OF_LETTERS, "The maximum number of letters must be %s",
			MAX_NUMBER_OF_LETTERS);

	}

	public static FirstName of(String value) {
		return new FirstName(value);
	}
}
