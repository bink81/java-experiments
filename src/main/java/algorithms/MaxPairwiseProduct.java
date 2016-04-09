package algorithms;

import java.util.Arrays;

public class MaxPairwiseProduct {
	static int getMaxPairwiseProduct(Integer[] numbers) {
		if (numbers.length == 1) {
			return 0;
		}
		Arrays.sort(numbers, (x, y) -> x - y);
		return numbers[numbers.length - 1] * numbers[numbers.length - 2];
	}

	public static void main(String[] args) {
		System.out.println(
				"Type number to calculate the maximum pairwise product. "
						+ "The first number is a total count of numbers.");
		FastScanner scanner = new FastScanner(System.in);
		int numberOfNumbers = scanner.popNextInt();
		if (numberOfNumbers > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Too many numbers, the max is: " + Integer.MAX_VALUE);
		}
		Integer[] numbers = new Integer[numberOfNumbers];
		for (int i = 0; i < numberOfNumbers; i++) {
			numbers[i] = scanner.popNextInt();
		}
		System.out.println(getMaxPairwiseProduct(numbers));
	}
}