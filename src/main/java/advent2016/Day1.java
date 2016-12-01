package advent2016;

import java.awt.Point;
import java.util.HashSet;

public class Day1 {

	public int calculateDistance(String input) {
		String[] movements = split(input);
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

			Integer distanceFrom = distanceFrom(movement);
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
		return Math.abs(x) + Math.abs(y);
	}

	private int determineDirection(int direction, char charAt) {
		switch (charAt) {
		case 'L':
			direction--;
			break;
		case 'R':
			direction++;
			break;
		default:
			throw new IllegalArgumentException("only L and R are allowed, but was: " + charAt);
		}
		if (direction < 0) {
			direction = 3;
		}
		else if (direction > 3) {
			direction = 0;
		}
		return direction;
	}

	public int calculateDistanceToTwice(String input) {
		HashSet<Point> points = new HashSet<>();
		String[] movements = split(input);

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

			Integer distanceFrom = distanceFrom(movement);
			for (int j = 0; j < distanceFrom; j++) {
				switch (direction) {
				case 0:
					y++;
					break;
				case 2:
					y--;
					break;
				case 1:
					x++;
					break;
				case 3:
					x--;
					break;
				default:
					throw new IllegalArgumentException(movement);
				}

				Point point = new Point(x, y);
				if (points.contains(point)) {
					return Math.abs(x) + Math.abs(y);
				}
				else {
					points.add(point);
				}
			}
		}
		return -1;
	}

	private String[] split(String input) {
		String[] movements = input.split(", ");
		return movements;
	}

	private Integer distanceFrom(String movement) {
		return Integer.valueOf(movement.substring(1));
	}
}
