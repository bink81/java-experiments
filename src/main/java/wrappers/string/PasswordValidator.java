package wrappers.string;

import wrappers.core.StringValidator;

public class PasswordValidator extends StringValidator {
	public PasswordValidator() {
		super("^.+$");
	}
}
