package utils;

public class PrimeUtils {

	// not practical for big numbers
	public static long getNextPrime(long n) {
		if (n == 0) {
			n = 1;
		}
		long i = n;
		for (; i < 2 * n; ++i) {
			long flag = 0;
			for (long divisor = 2; divisor < i; divisor++) {
				if (i % divisor == 0) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				return i;
			}
		}
		return 0;
	}
}
