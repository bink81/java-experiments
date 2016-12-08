package advent2016;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

	public String calculateCodePart1(List<String> input) {
		List<Integer> result = new ArrayList<>();
		int x = 1;
		int y = 1;
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
					throw new IllegalArgumentException("Unsupported characted: " + direction);
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

	private static final Point START_POINT = new Point(1, 3);

	public String calculateCodePart2(List<String> input) {
		List<Character> result = new ArrayList<>();
		List<String> panel = new ArrayList<>();
		panel.add("       ");
		panel.add("   1   ");
		panel.add("  234  ");
		panel.add(" 56789 ");
		panel.add("  ABC  ");
		panel.add("   D   ");
		panel.add("       ");
		Point currentPosition = START_POINT;
		Direction direction = Direction.WEST; // choosing any initial direction
		for (String directions : input) {
			for (int i = 0; i < directions.length(); i++) {
				char directionChar = directions.charAt(i);
				Point previous = new Point(currentPosition.x, currentPosition.y);
				if (directionChar == 'D') {
					direction = Direction.NORTH;
				}
				else if (directionChar == 'U') {
					direction = Direction.SOUTH;
				}
				else if (directionChar == 'R') {
					direction = Direction.EAST;
				}
				else if (directionChar == 'L') {
					direction = Direction.WEST;
				}
				currentPosition = direction.nextPosition(currentPosition);
				if (panel.get(currentPosition.y).charAt(currentPosition.x) == ' ') {
					currentPosition = previous;
				}
			}
			result.add(panel.get(currentPosition.y).charAt(currentPosition.x));
		}
		return result.toString();
	}
}
