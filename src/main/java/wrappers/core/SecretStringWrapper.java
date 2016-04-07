package wrappers.core;

import wrappers.core.StringWrapper;

public abstract class SecretStringWrapper extends StringWrapper {
	private static final long serialVersionUID = 1L;

	public static final String REPLACEMENT_TEXT = "<hidden>";

	public SecretStringWrapper(String value) {
		super(value);
	}

	@Override
	public String toString() {
		return REPLACEMENT_TEXT;
	}
}