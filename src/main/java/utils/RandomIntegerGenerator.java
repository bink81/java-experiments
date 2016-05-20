package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Preconditions;

/**
 * This class generates an array of random integers that are useful for testing.
 */
public class RandomIntegerGenerator {

	private final int amount;

	private final int min;

	private final int max;

	public RandomIntegerGenerator(int amount) {
		this(amount, Integer.MAX_VALUE, 0);
	}

	public RandomIntegerGenerator(int amount, int max) {
		this(amount, max, 0);
	}

	public RandomIntegerGenerator(int amount, int max, int min) {
		Preconditions.checkArgument(max > min, "Max must be bigger than Min");
		Preconditions.checkArgument(min >= 0, "Min must not be negative");
		Preconditions.checkArgument(amount >= 0, "Amount must not be negative");
		this.amount = amount;
		this.max = max;
		this.min = min;
	}

	public List<Integer> toList() {
		Random generator = new Random();
		List<Integer> list = new ArrayList<>(amount);
		for (int i = 0; i < amount; i++) {
			list.add(generator.nextInt(max - min) + min);
		}
		return list;
	}

	public int[] toArray() {
		return toList().stream().mapToInt(i -> i).toArray();
	}
}
