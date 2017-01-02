package advent2016;

import java.security.NoSuchAlgorithmException;

public class Day18 {

	private static final char TRAP = '^';
	private static final char FLOOR = '.';

	public long task1(String row, int size) throws NoSuchAlgorithmException {
		String nextRow = row;
		long counter = countSafeTiles(nextRow);
		for (int i = 0; i < size - 1; i++) {
			nextRow = generateNextRow(nextRow);
			counter += countSafeTiles(nextRow);
		}
		return counter;
	}

	private long countSafeTiles(String row) {
		return row.chars().filter(c -> c == FLOOR).count();
	}

	public String generateNextRow(String row) throws NoSuchAlgorithmException {
		StringBuffer rowBuffer = new StringBuffer();
		for (int i = 0; i < row.length(); i++) {
			boolean isTrapLeft = isTrap(row, i - 1);
			boolean isTrapCenter = isTrap(row, i);
			boolean isTrapRight = isTrap(row, i + 1);
			if (isTrapLeft && isTrapCenter && !isTrapRight || !isTrapLeft && isTrapCenter && isTrapRight
					|| isTrapLeft && !isTrapCenter && !isTrapRight
					|| !isTrapLeft && !isTrapCenter && isTrapRight) {
				rowBuffer.append(TRAP);
			} else {
				rowBuffer.append(FLOOR);
			}
		}
		return rowBuffer.toString();
	}

	private boolean isTrap(String row, int i) {
		if (i < 0 || i >= row.length()) {
			return false;
		}
		return row.charAt(i) == TRAP;
	}
}
