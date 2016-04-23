package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Preconditions;

public class RandomArrayGenerator {

	private final int size;

	private final int min;
	private final int max;

	public RandomArrayGenerator(int recordSize, int max) {
		this(recordSize, max, 0);
	}

	public RandomArrayGenerator(int recordSize, int max, int min) {
		Preconditions.checkArgument(max > min, "Max must be bigger than Min");
		this.size = recordSize;
		this.max = max;
		this.min = min;
	}

	public List<Integer> generateRandomNumbers() {
		Random generator = new Random();
		List<Integer> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			list.add(generator.nextInt(max - min) + min);
		}
		return list;
	}
}
