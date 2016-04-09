package algorithms;

public class MaxPairwiseProduct {
	static int getMaxPairwiseProduct(int[] numbers) {
		int result = 0;
		for (int i = 0; i < numbers.length; ++i) {
			for (int j = i + 1; j < numbers.length; ++j) {
				int pairwiseProduct = numbers[i] * numbers[j];
				if (pairwiseProduct > result) {
					result = pairwiseProduct;
				}
			}
		}
		return result;
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