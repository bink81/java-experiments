package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

	private static final int ABBA_LENGTH = 4;
	private static final int ABA_LENGTH = 3;

	public long task1(final File file) throws IOException {
		return Files.lines(file.toPath()).filter(line -> supportsTls(line.trim())).count();
	}

	public long task2(final File file) throws IOException {
		return Files.lines(file.toPath()).filter(line -> supportsSsl(line.trim())).count();
	}

	public boolean supportsTls(String s) {
		String[] split = s.split("\\[|\\]");
		List<String> supernetSequences = new ArrayList<>();
		List<String> hypernetSequences = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			if (i % 2 == 0) {
				supernetSequences.add(split[i]);
			} else {
				hypernetSequences.add(split[i]);
			}
		}
		if (abbaIn(hypernetSequences)) {
			return false;
		} else if (abbaIn(supernetSequences)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean abbaIn(List<String> sequences) {
		for (String sequence : sequences) {
			for (int i = 0; i < sequence.length() - ABBA_LENGTH + 1; i++) {
				if (isAbba(sequence, i)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isAbba(String s, int i) {
		if (s.charAt(i) == s.charAt(i + 1)) {
			return false;
		}
		String four = s.substring(i, i + ABBA_LENGTH);
		for (int j = 0; j < ABBA_LENGTH / 2; j++) {
			if (four.charAt(j) != four.charAt(ABBA_LENGTH - j - 1)) {
				return false;
			}
		}
		return true;
	}

	public boolean supportsSsl(String s) {
		String[] split = s.split("\\[|\\]");
		List<String> supernetSequences = new ArrayList<>();
		List<String> hypernetSequences = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			if (i % 2 == 0) {
				supernetSequences.add(split[i]);
			} else {
				hypernetSequences.add(split[i]);
			}
		}

		for (String supernetSequence : supernetSequences) {
			for (int i = 0; i < supernetSequence.length() - ABA_LENGTH + 1; i++) {
				if (isAba(supernetSequence, i)) {
					String aba = supernetSequence.substring(i, i + ABA_LENGTH);
					for (String hypernetSequence : hypernetSequences) {
						String reversed = "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1);
						if (hypernetSequence.contains(reversed)) {
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	public boolean isAba(String s, int i) {
		if (s.charAt(i) == s.charAt(i + 1)) {
			return false;
		}
		String three = s.substring(i, i + ABA_LENGTH);
		for (int j = 0; j < ABA_LENGTH / 2; j++) {
			if (three.charAt(j) != three.charAt(ABA_LENGTH - j - 1)) {
				return false;
			}
		}
		return true;
	}
}
