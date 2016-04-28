package algorithms;

import java.util.Arrays;

public class MathUtils {
	// Euclidean algorithm
	public static int greatestCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		}
		return greatestCommonDivisor(b, a % b);
	}

	public static long leastCommonMultiple(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		if (a == b) {
			return a;
		}
		return (long) a * b / greatestCommonDivisor(a, b);
	}

	public static long minimumDotProduct(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		long result = 0;
		for (int i = 0; i < a.length; i++) {
			result += (long) a[i] * b[b.length - i - 1];
		}
		return result;
	}
}
