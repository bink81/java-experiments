package algorithms;

public class MathUtils {
	public static int greatesCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		}
		return greatesCommonDivisor(b, a % b);
	}

	public static long leastCommonMultiple(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		if (a == b) {
			return a;
		}
		return (long) a * b / greatesCommonDivisor(a, b);
	}
}
