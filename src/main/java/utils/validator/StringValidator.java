package utils.validator;

public class StringValidator {

	private String pattern;

	public StringValidator(String pattern) {
		this.pattern = pattern;
	}

	public boolean isValid(String string) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = p.matcher(string);
		return m.matches();
	}
}
