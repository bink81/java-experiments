package wrappers.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements WrapperValidator {

	private String pattern;

	public StringValidator(String pattern) {
		this.setPattern(pattern);
	}

	@Override
	public boolean isValid(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Text must not be null!");
		}
		Pattern p = Pattern.compile(getPattern());
		Matcher m = p.matcher(text);
		return m.matches();
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
