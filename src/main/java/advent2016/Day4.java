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
		String nonCrcPart = roomNameWithCrcCode[0];
		int lastIndexOfRoomName = nonCrcPart.lastIndexOf(NAME_PART_DELIMETER);
		String roomName = nonCrcPart.substring(0, lastIndexOfRoomName);
		Comparator<Entry<Character, Integer>> reverseOrder =
				Collections.reverseOrder(Comparator.comparing(e -> e.getValue()));
		Comparator<Entry<Character, Integer>> order =
				reverseOrder.thenComparing(Entry<Character, Integer>::getKey);
		Map<Character, Integer> sortedCharMap = new LinkedHashMap<Character, Integer>();
		String roomNameWithoutDashes = roomName.replace(NAME_PART_DELIMETER, "");
		Map<Character, Integer> countedCharacters = countCharacters(roomNameWithoutDashes);
		String checksumPart = roomNameWithCrcCode[1].replace(CRC_SUFFIX, "");
		countedCharacters.entrySet().stream().sorted(order).limit(checksumPart.length()).forEachOrdered(
			e -> sortedCharMap.put(e.getKey(), e.getValue()));

		Iterator<Entry<Character, Integer>> iterator = sortedCharMap.entrySet().iterator();
		for (int i = 0; i < checksumPart.length(); i++) {
			Entry<Character, Integer> characterWithCount = iterator.next();
			if (characterWithCount.getKey() != checksumPart.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private Map<Character, Integer> countCharacters(final String original) {
		Map<Character, Integer> countedCharacters = new HashMap<>();
		for (int i = 0; i < original.length(); i++) {
			Character key = original.charAt(i);
			Integer count = countedCharacters.getOrDefault(key, 0);
			countedCharacters.put(key, count + 1);
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
