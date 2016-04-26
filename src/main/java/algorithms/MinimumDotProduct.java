package algorithms;

import java.util.Arrays;

public class MinimumDotProduct {
	public static long calculate(int[] input1, int[] input2) {
		Arrays.sort(input1);
		Arrays.sort(input2);
		long result = 0;
		for (int i = 0; i < input1.length; i++) {
			result += (long) input1[i] * input2[input2.length - i - 1];
		}
		return result;
	}
}
