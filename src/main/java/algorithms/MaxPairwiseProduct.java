package algorithms;

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
		return ((long) (numbers[maxIndex1])) * numbers[maxIndex2];
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