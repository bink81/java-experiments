package algorithms.greedy;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class FractionalKnapsack {
	public static double getBestValue(int capacity, int[] values, int[] weights) {
		double availableCapacity = capacity;
		double[] availableWeights = convertIntArrayToDoubleArray(weights);
		double totalValue = 0;
		Collection<Integer> positionsOrderedByValueToWeight = sort(values, weights);
		for (Integer mostValuableIndex : positionsOrderedByValueToWeight) {
			if (availableCapacity == 0) {
				return totalValue;
			}
			double wi = availableWeights[mostValuableIndex];
			if (wi == 0) {
				continue;
			}
			double availableSpace = Math.min(wi, availableCapacity);
			totalValue = totalValue + availableSpace * values[mostValuableIndex]
					/ availableWeights[mostValuableIndex];
			availableWeights[mostValuableIndex] =
					availableWeights[mostValuableIndex] - availableSpace;
			availableCapacity = availableCapacity - availableSpace;
		}
		return totalValue;
	}

	private static double[] convertIntArrayToDoubleArray(int[] w) {
		double[] availableWeights = new double[w.length];
		for (int i = 0; i < w.length; i++) {
			availableWeights[i] = w[i];
		}
		return availableWeights;
	}

	private static Collection<Integer> sort(int[] values, int[] weights) {
		SortedMap<Double, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
		for (int i = 0; i < values.length; i++) {
			int value = values[i];
			int weight = weights[i];
			if (weight == 0) {
				sortedMap.put(Double.POSITIVE_INFINITY, i);
			}
			else {
				sortedMap.put((double) value / weight, i);
			}
		}
		return sortedMap.values();
	}
}
