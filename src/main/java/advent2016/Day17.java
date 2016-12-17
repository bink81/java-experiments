package advent2016;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Day17 {

	private static final int MAZE_SIZE = 4;
	Queue<State> queue = new ArrayDeque<>();

	public String task1(String passcode) throws NoSuchAlgorithmException {
		return findPath(passcode, true);
	}

	public String task2(String passcode) throws NoSuchAlgorithmException {
		return findPath(passcode, false);
	}

	private String findPath(String passcode, boolean findShortest) throws NoSuchAlgorithmException {
		queue.add(new State(0, 0, ""));
		int maximumPathLength = 0;
		while (!queue.isEmpty()) {
			State head = queue.poll();
			if (head.getX() == 3 && head.getY() == 3) {
				if (findShortest) {
					return head.getPath();
				} else {
					if (head.getPath().length() > maximumPathLength) {
						maximumPathLength = head.getPath().length();
					}
					continue;
				}
			}
			String singleHash = createHash(passcode + head.getPath());
			if (isAvailable(0, head.getX(), head.getY(), singleHash)) {
				queue.add(new State(head.getX(), head.getY() - 1, head.getPath() + "U"));
			}
			if (isAvailable(1, head.getX(), head.getY(), singleHash)) {
				queue.add(new State(head.getX(), head.getY() + 1, head.getPath() + "D"));
			}
			if (isAvailable(2, head.getX(), head.getY(), singleHash)) {
				queue.add(new State(head.getX() - 1, head.getY(), head.getPath() + "L"));
			}
			if (isAvailable(3, head.getX(), head.getY(), singleHash)) {
				queue.add(new State(head.getX() + 1, head.getY(), head.getPath() + "R"));
			}
		}
		if (!findShortest) {
			return maximumPathLength + "";
		}
		return "-1";
	}

	boolean isAvailable(int direction, int x, int y, String hash) {
		switch (direction) {
		case 0:
			if (y > 0 && isDoorOpen(direction, hash)) {
				return true;
			}
			break;
		case 1:
			if (y < MAZE_SIZE - 1 && isDoorOpen(direction, hash)) {
				return true;
			}
			break;
		case 2:
			if (x > 0 && isDoorOpen(direction, hash)) {
				return true;
			}
			break;
		case 3:
			if (x < MAZE_SIZE - 1 && isDoorOpen(direction, hash)) {
				return true;
			}
			break;
		}
		return false;
	}

	private boolean isDoorOpen(int d, String singleHash) {
		char charAt = singleHash.charAt(d);
		if (charAt == 'b' || charAt == 'c' || charAt == 'd' || charAt == 'e' || charAt == 'f') {
			return true;
		}
		return false;
	}

	public class State {

		private final int x;
		private final int y;
		private final String path;

		public State(int x, int y, String path) {
			this.x = x;
			this.y = y;
			this.path = path;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String getPath() {
			return path;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", path=" + path + "]";
		}
	}

	String createHash(String plaintext) throws NoSuchAlgorithmException {
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
}
