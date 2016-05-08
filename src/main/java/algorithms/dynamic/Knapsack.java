package algorithms.dynamic;

public class Knapsack {
	static int optimalWeight(int totalWeight, int[] items) {
		int[][] values = new int[totalWeight + 1][items.length + 1];
		for (int i = 1; i <= items.length; i++) {
			for (int j = 1; j <= totalWeight; j++) {
				values[j][i] = values[j][i - 1];
				if (items[i - 1] <= j) {
					int val = values[j - items[i - 1]][i - 1] + items[i - 1];
					if (values[j][i] < val) {
						values[j][i] = val;
					}
				}
			}
		}
		int result = values[totalWeight][items.length];
		return result;
	}
}
