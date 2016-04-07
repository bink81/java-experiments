package wrappers.string;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.SecretStringWrapper;
import wrappers.core.ValueSafe;

/**
 * This class represents a pincode that must be made from up to 8 digits.
 * It should not expose the wrapped value in a clear text, by accident, in logs
 */
@ValueSafe
public final class Pincode extends SecretStringWrapper {
	private static final int MAX_NUMBER_OF_DIGITS = 8;

	private static final long serialVersionUID = 1664428264118410830L;

	private Pincode(String value) {
		super(value);
		checkArgument(
			new PincodeValidator().isValid(value), "Non digits detected in string  %s", value);
		checkArgument(
			value.length() <= MAX_NUMBER_OF_DIGITS, "The maximum number of digits must be %s",
			MAX_NUMBER_OF_DIGITS);
	}

	public static Pincode of(String value) {
		return new Pincode(value);
	}
}
