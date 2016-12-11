package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Day9 {

	public long task1(final File file) throws IOException {
		return Files.lines(file.toPath()).mapToLong(line -> decompress1(line)).sum();
	}

	public long decompress1(String s) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c == '(') {
				int nextParentisis = s.indexOf(")", i);
				String marker = s.substring(i + 1, nextParentisis);
				String[] split = marker.split("x");
				Integer characterCount = Integer.valueOf(split[0]);
				Integer repeatCount = Integer.valueOf(split[1]);
				i += nextParentisis - i + 1;
				String toRepeat = s.substring(i, i + characterCount);
				i += characterCount;
				for (int j = 0; j < repeatCount; j++) {
					sb.append(toRepeat);
				}
			} else {
				sb.append(c);
				i++;
			}
		}
		return sb.toString().length();
	}

	public long task2(final File file) throws IOException {
		return Files.lines(file.toPath()).mapToLong(line -> decompress2(line)).sum();
	}

	public long decompress2(String in) {
		// ArrayDeque<Character> a = new ArrayDeque<>();
		StringBuilder s = new StringBuilder(in);
		s = s.reverse();
		long count = 0;
		while (s.length() > 0) {
			int i = s.length() - 1;
			char c = s.charAt(i);
			if (c == '(') {
				int nextParentisis = s.lastIndexOf(")", i);
				String marker = s.substring(nextParentisis + 1, i);
				String[] split = new StringBuilder(marker).reverse().toString().split("x");
				int characterCount = Integer.valueOf(split[0]);
				int repeatCount = Integer.valueOf(split[1]);
				i -= i - nextParentisis - 1;
				int start = i - characterCount - 1;
				int end = i - 1;
				String toRepeat = s.substring(start, end);
				s.delete(start, s.length());
				i -= characterCount;
				for (int j = 0; j < repeatCount; j++) {
					s.append(toRepeat);
				}
			} else {
				count++;
				s.delete(s.length() - 1, s.length());
			}
		}
		return count;
	}
}
