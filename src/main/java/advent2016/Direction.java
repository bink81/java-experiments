package advent2016;

import java.awt.Point;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	// store it to avoid unnecessary recalculations
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

	public Direction determineDirection(char charAt) {
		switch (charAt) {
		case 'L':
			return previous();
		case 'R':
			return next();
		default:
			throw new IllegalArgumentException("only L and R are allowed, but was: " + charAt);
		}
	}

	public Point nextPosition(Point coordinates) {
		switch (this) {
		case NORTH:
			return new Point(coordinates.x, coordinates.y + 1);
		case SOUTH:
			return new Point(coordinates.x, coordinates.y - 1);
		case EAST:
			return new Point(coordinates.x + 1, coordinates.y);
		case WEST:
			return new Point(coordinates.x - 1, coordinates.y);
		default:
			throw new IllegalArgumentException();
		}
	}
}
