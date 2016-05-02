package algorithms.divide;
import java.util.Random;

/**
 * This is a randomized, three-way partitioned quick sort algorithm. Efficiency: worst case: O(n^2),
 * average case: O(n logn).
 */
public class QuickSort {
	private static Random random = new Random();

	private static int[] threeWayPartition(int[] a, int left, int right) {
		int m1 = left;
		int m2 = right;
		int i = left;
		int x = a[left];
		while (i <= m2) {
			if (a[i] < x) {
				swapPositions(a, m1, i);
				i++;
				m1++;
			} else if (a[i] > x) {
				swapPositions(a, m2, i);
				m2--;
			} else {
				i++;
			}
		}
		return new int[] { m1, m2 };
	}

	public static void sort(int[] a, int left, int right) {
		if (left >= right) {
			return;
		}
		int k = random.nextInt(right - left + 1) + left;
		swapPositions(a, left, k);
		int[] tuple = threeWayPartition(a, left, right);
		int m1 = tuple[0];
		int m2 = tuple[1];
		sort(a, left, m1 - 1);
		sort(a, m2 + 1, right);
	}

	private static void swapPositions(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
