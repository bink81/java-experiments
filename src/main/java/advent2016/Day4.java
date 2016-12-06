package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class Day4 {
	private static final String CRC_SUFFIX = "]";

	private static final String CRC_PREFIX = "[";

	private static final String ESCAPE_REGEX = "\\";

	private static final String NAME_PART_DELIMETER = "-";

	private static final int LETTER_COUNT = 26;

	private static final int POSISTION_OF_A = 97;

	public long task1(final File file) throws IOException {
		MutableValue<Long> sumOfSectorIds = new MutableValue<>();
		sumOfSectorIds.set(0L);

		Files.lines(file.toPath()).forEach(new Consumer<String>() {
			@Override
			public void accept(String line) {
				if (isReal(line)) {
					Long sectorId = extractId(line);
					sumOfSectorIds.set(sumOfSectorIds.get() + sectorId);
				}
			}
		});
		return sumOfSectorIds.get();
	}

	private Long extractId(final String line) {
		String[] split = line.split(NAME_PART_DELIMETER);
		String sectorIdWithCrcCode = split[split.length - 1];
		Long sectorId = Long
			.valueOf(sectorIdWithCrcCode.substring(0, sectorIdWithCrcCode.indexOf(CRC_PREFIX)));
		return sectorId;
	}

	public long task2(final File file) throws IOException {
		MutableValue<Long> result = new MutableValue<>();
		Files.lines(file.toPath()).forEach(new Consumer<String>() {
			@Override
			public void accept(String line) {
				if (isReal(line)) {
					Long id = extractId(line);
					String decrypted = decryptRoomName(line.substring(0, line.indexOf(CRC_PREFIX)));
					if (decrypted.equals("northpole object storage")) {
						result.set(id);
					}
				}
			}
		});
		return result.get();
	}

	public boolean isReal(final String room) {
		String[] roomNameWithCrcCode = room.split(ESCAPE_REGEX + CRC_PREFIX);
		String nonCrcString = roomNameWithCrcCode[0];
		String roomName = nonCrcString.substring(0, nonCrcString.lastIndexOf(NAME_PART_DELIMETER));
		String checksumString = roomNameWithCrcCode[1].replace(CRC_SUFFIX, "");

		Map<Character, Integer> sortedFiveMostFrequentCharacters =
				sortFiveMostFrequentCharacters(roomName, checksumString);

		return isCheckSumMadeFromFiveMostFrequentCharacters(
			sortedFiveMostFrequentCharacters, checksumString);
	}

	private Map<Character, Integer> sortFiveMostFrequentCharacters(
			final String roomName,
			final String checksum) {
		Comparator<Entry<Character, Integer>> reverseOrder =
				Collections.reverseOrder(Comparator.comparing(e -> e.getValue()));
		Comparator<Entry<Character, Integer>> order =
				reverseOrder.thenComparing(Entry<Character, Integer>::getKey);
		Map<Character, Integer> sortedFiveMostFrequentCharacters =
				new LinkedHashMap<Character, Integer>();
		String roomNameWithoutDashes = roomName.replace(NAME_PART_DELIMETER, "");
		Map<Character, Integer> countedCharacters = countCharacters(roomNameWithoutDashes);
		countedCharacters.entrySet().stream().sorted(order).limit(checksum.length()).forEachOrdered(
			e -> sortedFiveMostFrequentCharacters.put(e.getKey(), e.getValue()));
		return sortedFiveMostFrequentCharacters;
	}

	private boolean isCheckSumMadeFromFiveMostFrequentCharacters(
			final Map<Character, Integer> sortedFiveMostFrequentCharacters,
			final String checksumPart) {
		Iterator<Entry<Character, Integer>> iterator =
				sortedFiveMostFrequentCharacters.entrySet().iterator();
		for (int i = 0; i < checksumPart.length(); i++) {
			if (iterator.next().getKey() != checksumPart.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private Map<Character, Integer> countCharacters(final String original) {
		Map<Character, Integer> countedCharacters = new HashMap<>();
		for (int i = 0; i < original.length(); i++) {
			Character key = original.charAt(i);
			countedCharacters.put(key, countedCharacters.getOrDefault(key, 0) + 1);
		}
		return countedCharacters;
	}

	public String decryptRoomName(final String encryptedRoomName) {
		int lastIndexOfName = encryptedRoomName.lastIndexOf(NAME_PART_DELIMETER);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lastIndexOfName; i++) {
			char currentCharacter = encryptedRoomName.charAt(i);
			if (currentCharacter == NAME_PART_DELIMETER.charAt(0)) {
				sb.append(' ');
			}
			else {
				String sectorIdString = encryptedRoomName.substring(lastIndexOfName + 1);
				Long sectorId = Long.valueOf(sectorIdString);
				long shiftingFactor = currentCharacter + sectorId - POSISTION_OF_A;
				char decryptedChar = (char) (shiftingFactor % LETTER_COUNT + POSISTION_OF_A);
				sb.append(decryptedChar);
			}
		}
		return sb.toString();
	}
}
