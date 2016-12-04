package advent2016;

public class CharacterWithCount implements Comparable<CharacterWithCount> {
	private final char character;
	private final int count;

	public CharacterWithCount(char character, int count) {
		super();
		this.character = character;
		this.count = count;
	}

	public char getCharacter() {
		return character;
	}

	public int getCount() {
		return count;
	}

	@Override
	public int compareTo(CharacterWithCount o) {
		int diff = getCount() - o.getCount();
		if (diff != 0) {
			return diff;
		}
		diff = o.getCharacter() - getCharacter();
		return diff;
	}

	@Override
	public String toString() {
		return "[" + character + ", " + count + "]";
	}
}
