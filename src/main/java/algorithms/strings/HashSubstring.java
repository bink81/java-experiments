package algorithms.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.PrimeUtils;

public class HashSubstring {

	public static List<Integer> getOccurrencesNaively(Data input) {
		String pattern = input.getPattern(), text = input.getText();
		List<Integer> occurrences = new ArrayList<Integer>();
		for (int i = 0; i + pattern.length() <= text.length(); ++i) {
			boolean equal = true;
			for (int j = 0; j < pattern.length(); ++j) {
				if (pattern.charAt(j) != text.charAt(i + j)) {
					equal = false;
					break;
				}
			}
			if (equal) {
				occurrences.add(i);
			}
		}
		return occurrences;
	}

	public static List<Integer> getOccurrences(Data input) {
		String pattern = input.getPattern();
		String text = input.getText();
		List<Integer> occurrences = new ArrayList<Integer>();
		long p = PrimeUtils.findBigPrime();
		long x = generateRandomNumberLimitedBy(p);
		long pHash = polynomialHash(pattern, p, x);
		long[] h = precomputeHashes(text, pattern.length(), p, x);
		for (int i = 0; i <= text.length() - pattern.length(); ++i) {
			if (pHash != h[i]) {
				continue;
			}
			String substring = text.substring(i, i + pattern.length());
			if (areEqual(substring, pattern)) {
				occurrences.add(i);
			}
		}
		return occurrences;
	}

	private static boolean areEqual(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private static long generateRandomNumberLimitedBy(long p) {
		Random generator = new Random();
		long a = (long) (generator.nextDouble() * p) + 1;
		return (a % p + p) % p;
	}

	private static long[] precomputeHashes(String text, int pLength, long p, long x) {
		long[] h = new long[text.length() - pLength + 1];
		String s = text.substring(text.length() - pLength, text.length());
		h[text.length() - pLength] = polynomialHash(s, p, x);
		long y = 1;
		for (int i = 1; i <= pLength; i++) {
			long a = y * x;
			y = (a % p + p) % p;
		}
		for (int i = text.length() - pLength - 1; i >= 0; i--) {
			char previousChar = text.charAt(i);
			char nextChar = text.charAt(i + pLength);
			long a = x * h[i + 1] + previousChar - y * nextChar;
			h[i] = (a % p + p) % p;
		}
		return h;
	}

	private static long polynomialHash(String s, long p, long x) {
		long hash = 0;
		for (int i = s.length() - 1; i >= 0; --i) {
			long a = hash * x + s.charAt(i);
			hash = (a % p + p) % p;
		}
		return hash;
	}

	static class Data {
		private final String pattern;
		private final String text;

		public Data(String pattern, String text) {
			this.pattern = pattern;
			this.text = text;
		}

		public String getPattern() {
			return pattern;
		}

		public String getText() {
			return text;
		}
	}
}
