package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Day3 {
	private static final String NUMBER_DELIMETER = "\\s+";

	public long task1(File file) throws IOException {
		return Files
			.lines(file.toPath()).map(line -> new Triangle(line))
			.filter(triangle -> triangle.isReal()).count();
	}

	public int task2(File file) throws IOException {
		int triangleCount = 0;
		List<List<Integer>> columns = new ArrayList<List<Integer>>(3);
		columns.add(new ArrayList<>());
		columns.add(new ArrayList<>());
		columns.add(new ArrayList<>());

		Files.lines(file.toPath()).forEach(new Consumer<String>() {
			@Override
			public void accept(String text) {
				String[] split = text.trim().split(NUMBER_DELIMETER);
				columns.get(0).add(Integer.valueOf(split[0].trim()));
				columns.get(1).add(Integer.valueOf(split[1].trim()));
				columns.get(2).add(Integer.valueOf(split[2].trim()));
			}
		});

		for (List<Integer> list : columns) {
			for (int i = 0; i < list.size(); i += 3) {
				Triangle triangle = new Triangle(list.get(i), list.get(i + 1), list.get(i + 2));
				if (triangle.isReal()) {
					triangleCount++;
				}
			}
		}
		return triangleCount;
	}

	class Triangle {
		private final int x;

		private final int y;

		private final int z;

		public Triangle(String text) {
			String[] split = text.trim().split(NUMBER_DELIMETER);
			x = Integer.valueOf(split[0].trim());
			y = Integer.valueOf(split[1].trim());
			z = Integer.valueOf(split[2].trim());
		}

		public Triangle(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public boolean isReal() {
			if (x + y > z && x + z > y && y + z > x) {
				return true;
			}
			return false;
		}
	}
}
