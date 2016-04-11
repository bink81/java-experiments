package financial;

import java.util.Currency;

import org.junit.Test;

import junit.framework.Assert;

public class MixedCurrenciesExceptionTest {
	@Test
	public void testCreationCurrency1() throws Exception {
		Currency currency1 = Currency.getInstance("DKK");
		Currency currency2 = Currency.getInstance("EUR");
		MixedCurrenciesException mixedCurrenciesException =
				new MixedCurrenciesException(currency1, currency2);

		Assert.assertEquals(currency1, mixedCurrenciesException.getCurrency1());
	}

	@Test
	public void testCreationCurrency2() throws Exception {
		Currency currency1 = Currency.getInstance("DKK");
		Currency currency2 = Currency.getInstance("EUR");
		MixedCurrenciesException mixedCurrenciesException =
				new MixedCurrenciesException(currency1, currency2);

		Assert.assertEquals(currency2, mixedCurrenciesException.getCurrency2());
	}
}
