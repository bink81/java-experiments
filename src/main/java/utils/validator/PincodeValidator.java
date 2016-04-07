package utils.validator;

public class PincodeValidator extends StringValidator {
	public PincodeValidator() {
		super("^\\d+$");
	}
}
