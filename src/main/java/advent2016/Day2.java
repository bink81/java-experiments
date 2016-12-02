package advent2016;

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

		int x = 1;
		int y = 3;
		for (String directions : input) {
			for (int i = 0; i < directions.length(); i++) {
				char direction = directions.charAt(i);
				int backupX = x;
				int backupY = y;
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

				if (panel.get(y).charAt(x) == ' ') {
					x = backupX;
					y = backupY;
				}
			}
			result.add(panel.get(y).charAt(x));
		}
		return result.toString();
	}

}
