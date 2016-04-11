package financial;

import java.util.Currency;

public class MixedCurrenciesException extends RuntimeException {

	private static final long serialVersionUID = -3135193963082510370L;

	private final Currency currency1;

	private final Currency currency2;

	public MixedCurrenciesException(Currency currency1, Currency currency2) {
		this.currency1 = currency1;
		this.currency2 = currency2;
	}

	public Currency getCurrency1() {
		return currency1;
	}

	public Currency getCurrency2() {
		return currency2;
	}
}
