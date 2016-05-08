package algorithms.dynamic;

public class StringDistance {

	public static int calculateDistance(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] minimumDistances = initializeCalculationMatrix(n, m);
		for (int j = 1; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				int insertion = minimumDistances[i][j - 1] + 1;
				int deletion = minimumDistances[i - 1][j] + 1;
				int match = minimumDistances[i - 1][j - 1];
				int mismatch = minimumDistances[i - 1][j - 1] + 1;
				int min = Math.min(insertion, deletion);
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					minimumDistances[i][j] = Math.min(min, match);
				} else {
					minimumDistances[i][j] = Math.min(min, mismatch);
				}
			}
		}
		return minimumDistances[n][m];
	}

	private static int[][] initializeCalculationMatrix(int n, int m) {
		int[][] minimumDistances = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			minimumDistances[i][0] = i;
		}
		for (int j = 1; j <= m; j++) {
			minimumDistances[0][j] = j;
		}
		return minimumDistances;
	}
}
