package algorithms;

public class MathUtils {
	public static int greatesCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		}
		return greatesCommonDivisor(b, a % b);
	}
}
