package wrappers.string;

import wrappers.core.StringValidator;

public class NameValidator extends StringValidator {
	public NameValidator() {
		super("^\\w+$");
	}
}
