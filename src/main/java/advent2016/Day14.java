package advent2016;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

	private static final int MAX = 22552;// Integer.MAX_VALUE;
	List<String> hashes = new ArrayList<>();
	Map<Integer, List<Integer>> triplets = new HashMap<>();

	String createHash(final String id, int i, boolean stretching) throws NoSuchAlgorithmException {
		if (hashes.size() < i + 1) {
			String plaintext = id + String.valueOf(i);
			String hashString = singleHash(plaintext);
			if (stretching) {
				for (int j = 0; j < 2016; j++) {
					hashString = singleHash(hashString);
				}
			}
			hashes.add(hashString);
			return hashString;
		} else {
			return hashes.get(i);
		}
	}

	private String singleHash(String plaintext) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		BigInteger bigInt = new BigInteger(1, m.digest());
		String hashString = bigInt.toString(16);
		return prependWithZeros(hashString, 32);
	}

	public String prependWithZeros(String text, int size) {
		StringBuilder builder = new StringBuilder();
		while (builder.length() < size - text.length()) {
			builder.append('0');
		}
		return builder.append(text).toString();
	}

	public long getIndex2(String salt, int key) throws NoSuchAlgorithmException {
		return getCommonIndex1(salt, key, true);
	}

	public long getIndex1(String salt, int key) throws NoSuchAlgorithmException {
		return getCommonIndex1(salt, key, false);
	}

	public long getCommonIndex1(String salt, int key, boolean stretching)
			throws NoSuchAlgorithmException {
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < MAX; i++) {
			String h = createHash(salt, i, stretching);
			List<Integer> seq3 = getSequence(h, 3);
			if (!seq3.isEmpty()) {
				triplets.put(i, seq3);
				for (int j = i + 1; j < i + 1000; j++) {
					String newH = createHash(salt, j, stretching);
					List<Integer> seq5 = getSequence(newH, 5);
					if (!seq5.isEmpty()) {
						for (Integer pos5 : seq5) {
							for (Integer pos3 : seq3) {
								if (h.charAt(pos3) == newH.charAt(pos5)) {
									res.add(i);
									if (res.size() == key) {
										return res.get(key - 1);
									}
								}
							}
							break;
						}
					}
				}
			}
		}
		return -1;

	}

	private boolean isMatch(String h, List<Integer> seq3, String newH, List<Integer> seq5) {
		for (Integer pos5 : seq5) {
			for (Integer pos3 : seq3) {
				if (h.charAt(pos3) == newH.charAt(pos5)) {
					return true;
				}
			}
		}
		return false;
	}

	List<Integer> getSequence(String hashString, int length) {
		List<Integer> triplets = new ArrayList<>();
		char previous = 0;
		for (int i = 0; i < hashString.length(); i++) {
			char charAt = hashString.charAt(i);
			if (previous == charAt) {
				continue;
			}
			for (int j = i + 1; j < i + length && i + length - 1 < hashString.length(); j++) {
				if (charAt != hashString.charAt(j)) {
					break;
				}
				if (j - i + 1 == length) {
					triplets.add(i);
					i = j;
					break;
				}
			}
			previous = charAt;
		}
		return triplets;
	}
}
