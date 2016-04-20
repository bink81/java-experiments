package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArrayGenerator {

	private final int size;

	private final int bound;

	public RandomArrayGenerator(int recordSize, int bound) {
		this.size = recordSize;
		this.bound = bound;
	}

	public List<Integer> generateRandomNumbers() {
		List<Integer> list = new ArrayList<>(size);
		Random generator = new Random(33211234);
		for (int i = 0; i < size; i++) {
			list.add(generator.nextInt(bound));
		}
		return list;
	}
}
