package wrappers.string;

import wrappers.core.StringWrapper;
import wrappers.core.ValueSafe;

@ValueSafe
/**
 * Prevents exposing a password in a clear text, by accident, in logs
 */
public final class Password extends StringWrapper {
	private static final long serialVersionUID = 1664428264118410830L;

	public static final String DISPLAYED_TEXT = "<hidden>";

	private Password(String value) {
		super(value);
	}

	@Override
	public String toString() {
		return DISPLAYED_TEXT;
	}

	// constructors
	public static Password of(String value) {
		return new Password(value);
	}

	public static Password ofNullable(String value) {
		if (value == null) {
			return null;
		}
		else {
			return new Password(value);
		}
	}

	// getter with nullable
	public static String getNullable(Password password) {
		if (password == null) {
			return null;
		}
		else {
			return password.get();
		}
	}
}
