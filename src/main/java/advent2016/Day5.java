package advent2016;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Day5 {

	private static final int FIRST_SIGNIFICANT_INDEX = 5;

	private static final int SECOND_SIGNIFICANT_INDEX = 6;

	private static final int ASCI_INDEX_OF_0 = 48;

	private static final String DESIRED_PREFIX = "00000";

	private static final int CODE_LENGTH = 8;

	public String task1(final String id)
			throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String hashString = createHash(id, i);
			if (hashString.startsWith(DESIRED_PREFIX)) {
				sb.append(hashString.charAt(FIRST_SIGNIFICANT_INDEX));
			}
			if (sb.length() >= CODE_LENGTH) {
				break;
			}
		}
		return sb.toString();
	}

	public String task2(final String id)
			throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		Character[] result = new Character[8];
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String hashString = createHash(id, i);
			if (hashString.startsWith(DESIRED_PREFIX)) {
				char position = hashString.charAt(FIRST_SIGNIFICANT_INDEX);
				if (position >= '0' && position < '8'
						&& result[calculateIndexFromCharacter(position)] == null) {
					result[calculateIndexFromCharacter(position)] =
							hashString.charAt(SECOND_SIGNIFICANT_INDEX);
				}
			}
			if (calculateFilledCharacters(result) >= CODE_LENGTH) {
				break;
			}
		}
		return Arrays.toString(result);
	}

	private int calculateIndexFromCharacter(char position) {
		return position - ASCI_INDEX_OF_0;
	}

	private int calculateFilledCharacters(Character[] sb) {
		int count = 0;
		for (int j = 0; j < sb.length; j++) {
			if (sb[j] != null) {
				count++;
			}
		}
		return count;
	}

	private String createHash(final String id, int i) throws NoSuchAlgorithmException {
		String plaintext = id + String.valueOf(i);
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		BigInteger bigInt = new BigInteger(1, m.digest());
		String hashString = bigInt.toString(16);
		// pad with zero it to get the full 32 characters
		while (hashString.length() < 32) {
			hashString = "0" + hashString;
		}
		return hashString;
	}
}
