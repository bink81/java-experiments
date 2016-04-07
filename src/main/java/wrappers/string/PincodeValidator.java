package wrappers.string;

import wrappers.core.StringValidator;

public class PincodeValidator extends StringValidator {
	public PincodeValidator() {
		super("^\\d+$");
	}
}
