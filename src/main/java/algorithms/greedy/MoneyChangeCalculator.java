package algorithms.greedy;

import java.util.Arrays;

/**
 * Provides a minimum change (number of supported coins) for an amount
 */
public class MoneyChangeCalculator {

	public static int calculateMinimumChange(int amount, int[] coinTypes) {
		Arrays.sort(coinTypes);
		int remaining = amount;
		int numberOfCoins = 0;
		int tempAmount = 0;
		while (tempAmount < amount) {
			for (int i = coinTypes.length - 1; i >= 0; i--) {
				int coinType = coinTypes[i];
				if (coinType <= remaining) {
					remaining = remaining - coinType;
					numberOfCoins = numberOfCoins + 1;
					tempAmount = tempAmount + coinType;
					break;
				}
			}
		}
		int solutionWithoutGreedyAlgorithm = solutionWithoutGreedyAlgorithm(amount, coinTypes);
		if (numberOfCoins != solutionWithoutGreedyAlgorithm) {
			throw new RuntimeException(
					"Wrong numberOfCoins: " + numberOfCoins + " != " + solutionWithoutGreedyAlgorithm);
		}
		return numberOfCoins;
	}

	private static int solutionWithoutGreedyAlgorithm(int amount, int[] coinTypes) {
		if (coinTypes[0] != 1) {
			throw new IllegalArgumentException(
					"We only support coin types that have a coin with one unit");
		}
		int numberOfTens = amount / coinTypes[2];
		int amountWithoutTens = amount - coinTypes[2] * numberOfTens;
		int numberOfFives = amountWithoutTens / coinTypes[1];
		int numberOfOnes = amountWithoutTens - numberOfFives * coinTypes[1];
		int numberOfCoins = numberOfTens + numberOfFives + numberOfOnes;
		return numberOfCoins;
	}
}
