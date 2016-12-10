package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.IntStream;

public class Day6 {

	private static final Comparator<Entry<Character, Integer>> COMPARING_BY_VALUE = Map.Entry
			.comparingByValue();

	public String task1(final File file) throws IOException {
		return compareCharacters(file, COMPARING_BY_VALUE.reversed());
	}

	public String task2(final File file) throws IOException {
		return compareCharacters(file, COMPARING_BY_VALUE);
	}

	private String compareCharacters(final File file,
			final Comparator<Entry<Character, Integer>> comparator) throws IOException {
		List<Map<Character, Integer>> counts = new ArrayList<>();
		Files.lines(file.toPath()).forEach(line -> {
			IntStream.iterate(0, i -> i + 1).limit(line.length()).forEachOrdered(i -> {
				if (counts.size() < i + 1) {
					counts.add(new HashMap<>());
				}
				Map<Character, Integer> map = counts.get(i);
				map.put(line.charAt(i), map.getOrDefault(line.charAt(i), 0) + 1);
			});
		});

		StringBuffer sb = new StringBuffer();
		for (Map<Character, Integer> map : counts) {
			Optional<Entry<Character, Integer>> findFirst = map.entrySet().stream().sorted(comparator)
					.findFirst();
			sb.append(findFirst.isPresent() ? findFirst.get().getKey() : "");
		}
		return sb.toString();
	}
}
