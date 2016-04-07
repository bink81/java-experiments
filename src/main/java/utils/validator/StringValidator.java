package utils.validator;

public class StringValidator {

	private String pattern;

	public StringValidator(String pattern) {
		this.setPattern(pattern);
	}

	public boolean isValid(String string) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(getPattern());
		java.util.regex.Matcher m = p.matcher(string);
		return m.matches();
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
