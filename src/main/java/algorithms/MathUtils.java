package algorithms;

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
}
