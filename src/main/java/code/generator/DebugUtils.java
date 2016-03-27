package code.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// A convenience class for debugging the codes
public class DebugUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebugUtils.class);

	private static final String EOL_REGEX = "\\r?\\n";

	public static void compareStringsByCharacters(String reference, String actual) throws ComparisonDifferenceException {
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

	public static void compareStringsByLines(String string1, String string2) throws ComparisonDifferenceException {
		String[] referenceCharacters = string1.split(EOL_REGEX);
		String[] actualCharacters = string2.split(EOL_REGEX);
		int minimalLength = Math.min(referenceCharacters.length, actualCharacters.length);
		LOGGER.debug("minimalLength={}", minimalLength);
		for (int i = 0; i < minimalLength; i++) {
			if (!referenceCharacters[i].equals(actualCharacters[i])) {
				throw new ComparisonDifferenceException("" + referenceCharacters[i], "" + actualCharacters[i], i);
			}
		}
	}

	public static String readFileContentAsString(Path path) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, StandardCharsets.UTF_8);
	}
}
