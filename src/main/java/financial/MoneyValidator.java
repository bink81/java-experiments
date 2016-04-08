package financial;

import wrappers.core.StringValidator;

public class MoneyValidator extends StringValidator {
	public MoneyValidator() {
		super("^\\d+(.\\d+)?$");
	}
}
