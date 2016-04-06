package wrappers.string;

import static com.google.common.base.Preconditions.checkArgument;

import utils.validator.EmailAddressValidator;
import wrappers.core.StringWrapper;
import wrappers.core.ValueSafe;

@ValueSafe
public final class Email extends StringWrapper {
	private static final long serialVersionUID = 1626599534467049198L;

	public Email(String value) {
		super(value);
		checkArgument(new EmailAddressValidator().isValid(value));
	}
}
