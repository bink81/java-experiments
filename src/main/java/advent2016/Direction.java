package advent2016;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	private static Direction[] vals = values();

	public Direction previous() {
		int i = this.ordinal() - 1;
		if (i < 0) {
			i = vals.length - 1;
		}
		return vals[i % vals.length];
	}

	public Direction next() {
		return vals[(this.ordinal() + 1) % vals.length];
	}
}
