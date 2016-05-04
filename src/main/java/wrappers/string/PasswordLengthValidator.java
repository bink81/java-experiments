package wrappers.string;

import wrappers.core.StringValidator;

public class PasswordLengthValidator extends StringValidator {
	public static final String MINIMUM_LENGTH = "6";

	public static final String MAXIMUM_LENGTH = "20";

	public PasswordLengthValidator() {
		super(assembleLengthCheck(), assembleLowerCaseCheck());
	}

	private static String assembleLengthCheck() {
		return "^(.+){" + MINIMUM_LENGTH + "," + MAXIMUM_LENGTH + "}$";
	}

	private static String assembleLowerCaseCheck() {
		return ".*[a-z]";
	}
}
