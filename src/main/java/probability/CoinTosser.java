package probability;

import java.util.Random;

public class CoinTosser {
	// The bigger is, the more accurate, at the cost of computational effort
	private static final int REPEATABILITY_NUMER = 100000;

	private final int numberOfCoinTosses;

	private final int numberOfHeadsInARow;

	public CoinTosser(int numberOfCoinTosses, int numberOfHeadsInARow) {
		this.numberOfCoinTosses = numberOfCoinTosses;
		this.numberOfHeadsInARow = numberOfHeadsInARow;
	}

	public double calculateProbability() {
		int successCount = 0;
		for (int i = 0; i < REPEATABILITY_NUMER; i++) {
			if (sequenceHappened())
				successCount++;
		}
		return (double) successCount / REPEATABILITY_NUMER;
	}

	private boolean sequenceHappened() {
		int headsCount = 0;
		for (int i = 0; i < getNumberOfCoinTosses(); i++) {
			int result = new Random().nextInt(2);
			if (result == 1) {
				headsCount++;
			}
			else {
				headsCount = 0;
			}
			if (headsCount >= getNumberOfHeadsInARow()) {
				return true;
			}
		}
		return false;
	}

	public int getNumberOfCoinTosses() {
		return numberOfCoinTosses;
	}

	public int getNumberOfHeadsInARow() {
		return numberOfHeadsInARow;
	}
}
