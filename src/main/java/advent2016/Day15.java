package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day15 {
	private static final int MAX = 10000000;

	Pattern givesPattern = Pattern
		.compile("Disc #\\d+ has (\\d+) positions; at time=0, it is at position (\\d+).");

	List<Integer> sizes = new ArrayList<>();

	List<Integer> initialPositions = new ArrayList<>();

	public long task1(File file) throws IOException, NoSuchAlgorithmException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		return IntStream.range(0, MAX).filter(i -> isPassageClear(i + 1)).findFirst().orElse(-1);
	}

	private Object parse(final String line) {
		Matcher givesMatch = givesPattern.matcher(line.trim());
		if (givesMatch.find()) {
			sizes.add(Integer.valueOf(givesMatch.group(1)));
			initialPositions.add(Integer.valueOf(givesMatch.group(2)));
		}
		return null;
	}

	private boolean isPassageClear(int releaseTime) {
		for (int j = 0; j < sizes.size(); j++) {
			int time = releaseTime + j;
			boolean diskGap = isDiskGap(sizes.get(j), initialPositions.get(j), time);
			if (!diskGap) {
				return false;
			}
		}
		return true;
	}

	public boolean isDiskGap(int size, int initial, int time) {
		if ((initial + time) % size == 0) {
			return true;
		}
		return false;
	}
}
