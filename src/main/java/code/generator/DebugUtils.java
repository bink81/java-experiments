package code.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// A convenience class for debugging the codes
public class DebugUtils {
	private static final String EOL_REGEX = "\\r?\\n";

	public static void compareStringsByCharacters(String string1, String string2) {
		char[] characters1 = string1.toCharArray();
		char[] characters2 = string2.toCharArray();
		int minLength = Math.min(characters1.length, characters2.length);
		for (int i = 0; i < minLength; i++) {
			if (characters1[i] != characters2[i]) {
				System.out.println("Different characters ('" + characters1[i] + "'!='" + characters2[i]
						+ "') at index " + i);
				return;
			}
		}
	}

	public static void compareStringsByLines(String string1, String string2) {
		String[] lines1 = string1.split(EOL_REGEX);
		String[] lines2 = string2.split(EOL_REGEX);
		int minLength = Math.min(lines1.length, lines2.length);
		for (int i = 0; i < minLength; i++) {
			if (!lines1[i].equals(lines2[i])) {
				System.out.println("Lines differ:\n" + lines1[i] + "\nVS\n" + lines2[i]
						+ "\n at line number " + i + ".");
				return;
			}
		}
	}

	public static String readFileContentAsString(Path path) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, StandardCharsets.UTF_8);
	}

	@FunctionalInterface
	interface CheckedRunnable {
		void run() throws Throwable;
	}

	public static void ignoreException(CheckedRunnable r) {
		try {
			r.run();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
