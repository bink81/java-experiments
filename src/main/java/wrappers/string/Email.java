package wrappers.string;

import static com.google.common.base.Preconditions.checkArgument;

import wrappers.core.StringWrapper;
import wrappers.core.ValueSafe;

@ValueSafe
public final class Email extends StringWrapper {
	private static final long serialVersionUID = 1626599534467049198L;

	public Email(String value) {
		super(value);
		EmailAddressValidator emailAddressValidator = new EmailAddressValidator();
		checkArgument(
			emailAddressValidator.isValid(value),
			"The value %s does match expected regular expression: %s", value,
			emailAddressValidator.getPatterns());
	}
}
