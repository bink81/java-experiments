package algorithms;

import com.google.common.base.Preconditions;

public class PolynomialUtils {

	// O(n^2)
	public static long[] multiply(int[] a, int[] b) {
		Preconditions.checkArgument(a != null, "a must not be null");
		Preconditions.checkArgument(b != null, "b must not be null");
		Preconditions.checkArgument(b.length == a.length,
				"Size of a must be equal to size of b: " + a.length + "<>" + b.length);
		int n = a.length;
		if (n == 0) {
			return new long[] { 0 };
		}
		Preconditions.checkArgument(n > 0, "n must be positive n: " + n);
		long[] product = new long[2 * n - 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				product[i + j] = product[i + j] + (long) a[i] * b[j];
			}
		}
		return product;
	}
}
