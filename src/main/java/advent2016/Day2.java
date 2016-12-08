package advent2016;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

	public String calculateCodePart1(final List<String> input) {
		int x = 1; // start position in the middle
		int y = 1;

		List<Integer> result = new ArrayList<>(input.size());
		for (String directions : input) {
			for (int i = 0; i < directions.length(); i++) {
				char direction = directions.charAt(i);
				switch (direction) {
				case 'D':
					y++;
					break;
				case 'U':
					y--;
					break;
				case 'R':
					x++;
					break;
				case 'L':
					x--;
					break;
				default:
					throw new IllegalArgumentException("Unsupported character: " + direction);
				}

				if (x < 0) {
					x = 0;
				}
				else if (x > 2) {
					x = 2;
				}
				if (y < 0) {
					y = 0;
				}
				else if (y > 2) {
					y = 2;
				}
			}
			int code = x + y * 3 + 1;
			result.add(code);
		}
		return result.toString();
	}

	public String calculateCodePart2(final List<String> input) {
		List<Character> result = new ArrayList<>();
		List<String> panel = new ArrayList<>();
		panel.add("       ");
		panel.add("   1   ");
		panel.add("  234  ");
		panel.add(" 56789 ");
		panel.add("  ABC  ");
		panel.add("   D   ");
		panel.add("       ");
		Point position = new Point(1, 3); // start position at button '5'

		for (String directions : input) {
			for (int i = 0; i < directions.length(); i++) {
				Point previousPosition = new Point(position.x, position.y);
				Direction direction = chooseDirection(directions.charAt(i));
				position = direction.nextPosition(position);
				if (findCharInPanel(panel, position) == ' ') {
					position = previousPosition;
				}
			}
			result.add(findCharInPanel(panel, position));
		}
		return result.toString();
	}

	private char findCharInPanel(List<String> panel, Point position) {
		return panel.get(position.y).charAt(position.x);
	}

	private Direction chooseDirection(final char directionChar) {
		if (directionChar == 'D') {
			return Direction.NORTH;
		}
		else if (directionChar == 'U') {
			return Direction.SOUTH;
		}
		else if (directionChar == 'R') {
			return Direction.EAST;
		}
		else if (directionChar == 'L') {
			return Direction.WEST;
		}
		else {
			throw new IllegalArgumentException("Unsupported character: " + directionChar);
		}
	}
}
