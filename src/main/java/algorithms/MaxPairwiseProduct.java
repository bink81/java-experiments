package algorithms;

import java.util.Arrays;

public class MaxPairwiseProduct {
	static long getMaxPairwiseProduct(int[] numbers) {
		if (numbers.length == 1) {
			return 0;
		}
		Integer maxIndex1 = null;
		Integer maxIndex2 = null;
		for (int i = 0; i < numbers.length; ++i) {
			if (maxIndex1 == null || numbers[i] > numbers[maxIndex1]) {
				maxIndex2 = maxIndex1;
				maxIndex1 = i;
			} else if (maxIndex2 == null || numbers[i] > numbers[maxIndex2]) {
				maxIndex2 = i;
			}
		}
		long result = ((long) (numbers[maxIndex1])) * numbers[maxIndex2];
		checkAgainstAlgorithmWithSorting(numbers, result);
		return result;
	}

	private static void checkAgainstAlgorithmWithSorting(int[] numbers, long result) {
		long result2 = getMaxPairwiseProductWithSorting(numbers);
		if (result != result2) {
			throw new RuntimeException("getMaxPairwiseProduct returned " + result
					+ " while getMaxPairwiseProductWithSorting returned " + result2);
		}
	}

	// This method is not as efficient (n logn) but it's used as a result verification experiment
	private static long getMaxPairwiseProductWithSorting(int[] numbers) {
		Integer[] newArray = new Integer[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			newArray[i] = numbers[i];
		}
		if (newArray.length == 1) {
			return 0;
		}
		Arrays.sort(newArray, (x, y) -> x - y);
		return (long) newArray[newArray.length - 1] * newArray[newArray.length - 2];
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
		int[] numbers = new int[numberOfNumbers];
		for (int i = 0; i < numberOfNumbers; i++) {
			numbers[i] = scanner.popNextInt();
		}
		System.out.println(getMaxPairwiseProduct(numbers));
	}
}