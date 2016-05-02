package algorithms.divide;

public class IntegerArraySearch {
	public static final int RESULT_NOT_FOUND = -1;

	static int binarySearch(int[] a, int number) {
		int low = 0;
		int high = a.length - 1;
		while (high >= low) {
			int middle = low + (high - low) / 2;
			if (number == a[middle]) {
				return middle;
			} else if (number < a[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return RESULT_NOT_FOUND;
	}

	static int linearSearch(int[] a, int number) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == number) {
				return i;
			}
		}
		return RESULT_NOT_FOUND;
	}
}
