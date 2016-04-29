package code.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

// A convenience class for debugging the codes
public class DebugUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebugUtils.class);

	private static final String EOL_REGEX = "\\r?\\n";

	public static void compareStringsByCharacters(String reference, String actual)
			throws ComparisonDifferenceException {
		checkParameters(reference, actual);
		char[] referenceCharacters = reference.toCharArray();
		char[] actualCharacters = actual.toCharArray();
		int minimalLength = Math.min(referenceCharacters.length, actualCharacters.length);
		LOGGER.debug("minimalLength={}", minimalLength);
		for (int i = 0; i < minimalLength; i++) {
			if (referenceCharacters[i] != actualCharacters[i]) {
				throw new ComparisonDifferenceException(
					"" + referenceCharacters[i], "" + actualCharacters[i], i);
			}
		}
	}

	public static void compareStringsByLines(String reference, String actual)
			throws ComparisonDifferenceException {
		checkParameters(reference, actual);
		String[] referenceCharacters = reference.split(EOL_REGEX);
		String[] actualCharacters = actual.split(EOL_REGEX);
		int minimalLength = Math.min(referenceCharacters.length, actualCharacters.length);
		LOGGER.debug("minimalLength={}", minimalLength);
		for (int i = 0; i < minimalLength; i++) {
			if (!referenceCharacters[i].equals(actualCharacters[i])) {
				throw new ComparisonDifferenceException(
					"" + referenceCharacters[i], "" + actualCharacters[i], i);
			}
		}
	}

	private static void checkParameters(String reference, String actual) {
		Preconditions.checkNotNull(reference, "reference must not be null!");
		Preconditions.checkNotNull(actual, "actual must not be null!");
	}

	public static String readFileContentAsString(Path path) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, StandardCharsets.UTF_8);
	}

	/**
	 * This method logs system properties
	 */
	public static void logEnvironmentProperties() {
		StringBuilder builder = new StringBuilder();
		for (String name : assembleSortedPropertyNames()) {
			String value = System.getProperty(name);
			builder.append("\n  ").append(name).append("=").append(value);
		}
		LOGGER.info(builder.toString());
	}

	private static List<String> assembleSortedPropertyNames() {
		List<String> propertyNames = Lists.newArrayList();
		Enumeration<?> e = System.getProperties().propertyNames();
		while (e.hasMoreElements()) {
			propertyNames.add((String) e.nextElement());
		}
		Collections.sort(propertyNames);
		return propertyNames;
	}
}
