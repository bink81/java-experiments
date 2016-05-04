package wrappers.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements WrapperValidator {

	private String[] patterns;

	public StringValidator(String... patterns) {
		this.patterns = new String[patterns.length];
		for (int i = 0; i < patterns.length; i++) {
			this.patterns[i] = patterns[i];
		}
	}

	@Override
	public boolean isValid(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Text must not be null!");
		}
		for (String pattern : patterns) {
			// FIXME RM:pattern NOCOMMIT
			System.err.println("#pattern=" + pattern);
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(text);
			if (!m.matches()) {
				return false;
			}
		}
		return true;
	}

	public String[] getPatterns() {
		return patterns;
	}

	public void setPattern(String[] patterns) {
		this.patterns = patterns;
	}
}
