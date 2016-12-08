package advent2016;

import java.awt.Point;
import java.util.HashSet;

public class Day1 {

	public int calculateDistance(final String input) {
		String[] movements = splitMovements(input);
		int x = 0;
		int y = 0;
		int direction = 0;
		// 0 - NORTH
		// 1 - EAST
		// 2 - SOUTH
		// 3 - WEST
		for (int i = 0; i < movements.length; i++) {
			String movement = movements[i];
			direction = determineDirection(direction, movement.charAt(0));

			Integer distanceFrom = distanceToTravel(movement);
			switch (direction) {
			case 0:
				y += distanceFrom;
				break;
			case 2:
				y -= distanceFrom;
				break;
			case 1:
				x += distanceFrom;
				break;
			case 3:
				x -= distanceFrom;
				break;
			default:
				throw new IllegalArgumentException(movement);
			}
		}
		return calculateDirectDistance(x, y);
	}

	private int determineDirection(int direction, final char rotation) {
		switch (rotation) {
		case 'L': // left
			direction--;
			break;
		case 'R': // right
			direction++;
			break;
		default:
			throw new IllegalArgumentException("only L and R are allowed, but was: " + rotation);
		}
		if (direction < 0) {
			direction = 3;
		}
		else if (direction > 3) {
			direction = 0;
		}
		return direction;
	}

	public int calculateDistanceToTwice(final String input) {
		Point currentPosition = new Point(0, 0);
		Direction direction = Direction.NORTH;

		HashSet<Point> points = new HashSet<>();
		String[] movements = splitMovements(input);
		for (int i = 0; i < movements.length; i++) {
			String movement = movements[i];
			direction = direction.determineDirection(movement.charAt(0));
			for (int j = 0; j < distanceToTravel(movement); j++) {
				currentPosition = direction.nextPosition(currentPosition);
				if (points.contains(currentPosition)) {
					return calculateDirectDistance(currentPosition);
				}
				else {
					points.add(currentPosition);
				}
			}
		}
		return -1;
	}

	private int calculateDirectDistance(final Point currentPosition) {
		return calculateDirectDistance(currentPosition.x, currentPosition.y);
	}

	private int calculateDirectDistance(final int x, final int y) {
		return Math.abs(x) + Math.abs(y);
	}

	private String[] splitMovements(final String input) {
		return input.split(", ");
	}

	private Integer distanceToTravel(final String movement) {
		return Integer.valueOf(movement.substring(1));
	}
}
