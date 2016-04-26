package algorithms;

public class FibonacciVariations {
	public static long calculateResult(int n) {
		if (n <= 1) {
			return n;
		}
		int previousLastNumber = 0;
		int lastNumber = 1;
		int tempNumber = 0;
		for (int i = 2; i <= n; i++) {
			tempNumber = lastNumber;
			lastNumber = lastNumber + previousLastNumber;
			previousLastNumber = tempNumber;
		}
		return lastNumber;
	}

	public static int calculateLastDigit(int n) {
		if (n <= 1) {
			return n;
		}
		int previousLastDigit = 0;
		int lastDigit = 1;
		int tempDigit = 0;
		for (int i = 2; i <= n; i++) {
			tempDigit = lastDigit;
			lastDigit = (lastDigit + previousLastDigit) % 10;
			previousLastDigit = tempDigit;
		}
		return lastDigit;
	}
}
