package algorithms;

/**
 * Provides a minimum change (number of supported coins) for an amount
 */
public class MoneyChangeCalculator {
	private static final int FIVE = 5;

	private static final int TEN = 10;

	public static int getChange(int amount) {
		int[] coinTypes = new int[] { 10, 5, 1 };
		int remaining = amount;
		int numberOfCoins = 0;
		int tempAmount = 0;
		while (tempAmount < amount) {
			for (int coinType : coinTypes) {
				if (coinType <= remaining) {
					remaining = remaining - coinType;
					numberOfCoins = numberOfCoins + 1;
					tempAmount = tempAmount + coinType;
					break;
				}
			}
		}
		if (numberOfCoins != solutionWithoutGreedyAlgorithm(amount)) {
			throw new RuntimeException("Wrong numberOfCoins: " + numberOfCoins);
		}
		return numberOfCoins;
	}

	private static int solutionWithoutGreedyAlgorithm(int amount) {
		int numberOfTens = amount / TEN;
		int amountWithoutTens = amount - TEN * numberOfTens;
		int numberOfFives = amountWithoutTens / FIVE;
		int numberOfOnes = amountWithoutTens - numberOfFives * FIVE;
		int numberOfCoins = numberOfTens + numberOfFives + numberOfOnes;
		return numberOfCoins;
	}
}
