package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Day8 {

	private static final String ACTION_ROTATE_COLUMN = "rotate column";
	private static final String ACTION_ROTATE_ROW = "rotate row";
	private static final String ACTION_RECT = "rect";

	private static final int SCREEN_HEIGHT = 6;
	private static final int SCREEN_WIDTH = 50;

	private boolean[][] screen = new boolean[SCREEN_HEIGHT + 1][SCREEN_WIDTH + 1];

	public long task1(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> updateScreen(line));
		return showEnabledPixels();
	}

	private void updateScreen(String line) {
		if (line.startsWith(ACTION_RECT)) {
			String args = line.substring(ACTION_RECT.length() + 1);
			String[] split = args.split("x");
			rect(Integer.valueOf(split[0].trim()), Integer.valueOf(split[1].trim()));
		} else if (line.startsWith(ACTION_ROTATE_ROW)) {
			String args = line.substring(ACTION_ROTATE_ROW.length() + 3);
			String[] split = args.split(" by ");
			rotateRow(Integer.valueOf(split[0].trim()), Integer.valueOf(split[1].trim()));
		} else if (line.startsWith(ACTION_ROTATE_COLUMN)) {
			String args = line.substring(ACTION_ROTATE_COLUMN.length() + 3);
			String[] split = args.split(" by ");
			rotateColumn(Integer.valueOf(split[0].trim()), Integer.valueOf(split[1].trim()));
		}
	}

	// private void demo() {
	// rect(3, 2);
	// rotateColumn(1, 1);
	// rotateRow(0, 4);
	// showEnabledPixels();
	// }

	private void rotateColumn(int column, int shifts) {
		for (int i = 0; i < shifts; i++) {
			for (int j = screen.length - 1; j > 0; j--) {
				screen[j][column] = screen[j - 1][column];
			}
			screen[0][column] = screen[screen.length - 1][column];
		}
	}

	private void rotateRow(int row, int shifts) {
		for (int i = 0; i < shifts; i++) {
			for (int j = screen[row].length - 1; j > 0; j--) {
				screen[row][j] = screen[row][j - 1];
			}
			screen[row][0] = screen[row][screen[row].length - 1];
		}
	}

	private void rect(int a, int b) {
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				screen[j][i] = true;
			}
		}
	}

	private long showEnabledPixels() {
		long count = 0;
		for (int i = 0; i < screen.length - 1; i++) {
			for (int j = 0; j < screen[i].length - 1; j++) {
				boolean pixelOn = screen[i][j];
				if (pixelOn) {
					count++;
				}
				System.err.print(pixelOn ? '#' : '.');
			}
			System.err.println();
		}
		return count;
	}
}
