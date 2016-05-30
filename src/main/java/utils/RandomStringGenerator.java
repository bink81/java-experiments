package utils;

import java.util.Random;

import com.google.common.base.Preconditions;

/**
 * This class generates an array of random integers that are useful for testing.
 */
public class RandomStringGenerator {

	private final int size;

	private final int firstAsciCharacter;

	private final int lastAsciCharacter;

	public RandomStringGenerator(int size) {
		this(size, '}', '!');
	}

	public RandomStringGenerator(int size, int lastAsciCharacter) {
		this(size, lastAsciCharacter, '}');
	}

	public RandomStringGenerator(int size, int lastAsciCharacter, int firstAsciCharacter) {
		Preconditions.checkArgument(lastAsciCharacter > firstAsciCharacter,
				"First ASCI character (" + firstAsciCharacter +
						") must be bigger than last (" + lastAsciCharacter
						+ ")");
		Preconditions.checkArgument(firstAsciCharacter >= 0,
				"Last ASCI character must not be negative");
		Preconditions.checkArgument(size >= 0, "Size must not be negative");
		this.size = size;
		this.lastAsciCharacter = lastAsciCharacter;
		this.firstAsciCharacter = firstAsciCharacter;
	}

	public String produceString() {
		char[] data = new char[size];
		for (int i = 0; i < size; i++) {
			int range = lastAsciCharacter - firstAsciCharacter;
			data[i] = (char) (new Random().nextInt(range) + firstAsciCharacter);
		}
		return String.valueOf(data);
	}
}
