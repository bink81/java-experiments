package utils.validator;

public class PasswordValidator extends StringValidator {
	public PasswordValidator() {
		super("^.+$");
	}
}
