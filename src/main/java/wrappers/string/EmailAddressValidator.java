package wrappers.string;

import wrappers.core.StringValidator;

public class EmailAddressValidator extends StringValidator {
	public EmailAddressValidator() {
		super(
			"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
	}
}
