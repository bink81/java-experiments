package code.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

// A convenience class for debugging the codes
public class DebugUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebugUtils.class);

	private static final String EOL_REGEX = "\\r?\\n";

	public static void compareStringsByCharacters(String reference, String actual) throws ComparisonDifferenceException {
		checkParameters(reference, actual);
		char[] referenceCharacters = reference.toCharArray();
		char[] actualCharacters = actual.toCharArray();
		int minimalLength = Math.min(referenceCharacters.length, actualCharacters.length);
		LOGGER.debug("minimalLength={}", minimalLength);
		for (int i = 0; i < minimalLength; i++) {
			if (referenceCharacters[i] != actualCharacters[i]) {
				throw new ComparisonDifferenceException("" + referenceCharacters[i], "" + actualCharacters[i], i);
			}
		}
	}

	public static void compareStringsByLines(String reference, String actual) throws ComparisonDifferenceException {
		checkParameters(reference, actual);
		String[] referenceCharacters = reference.split(EOL_REGEX);
		String[] actualCharacters = actual.split(EOL_REGEX);
		int minimalLength = Math.min(referenceCharacters.length, actualCharacters.length);
		LOGGER.debug("minimalLength={}", minimalLength);
		for (int i = 0; i < minimalLength; i++) {
			if (!referenceCharacters[i].equals(actualCharacters[i])) {
				throw new ComparisonDifferenceException("" + referenceCharacters[i], "" + actualCharacters[i], i);
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
}
