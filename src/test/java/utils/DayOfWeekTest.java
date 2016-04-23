package utils;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class DayOfWeekTest {
	private static final int VERY_FUTURE_DATE = 99999999;
	private static final int START_OF_GREGORIAN_CALENDAR = 1582;
	private static final int NUMBER_OF_RANDOM_TESTS = 1000;

	private static final int FIRST_DAY_OF_A_MONTH = 1;
	private static final int FIRST_MONTH = 1;

	private static final int NUMER_OF_MONTHS = 12;
	private static final int MAX_NUMBER_OF_DAYS_IN_A_MONTH = 28;

	@Test
	public void test() throws Exception {
		int actual = DayOfWeek.calculate(2016, 4, 22);

		Assert.assertEquals(5, actual);
	}

	@Test
	public void testRandom() throws Exception {
		Calendar cal = Calendar.getInstance();
		List<Integer> years = new RandomArrayGenerator(NUMBER_OF_RANDOM_TESTS, VERY_FUTURE_DATE,
				START_OF_GREGORIAN_CALENDAR).generateRandomNumbers();
		List<Integer> months = new RandomArrayGenerator(NUMBER_OF_RANDOM_TESTS, NUMER_OF_MONTHS,
				FIRST_MONTH).generateRandomNumbers();
		List<Integer> days = new RandomArrayGenerator(NUMBER_OF_RANDOM_TESTS,
				MAX_NUMBER_OF_DAYS_IN_A_MONTH, FIRST_DAY_OF_A_MONTH).generateRandomNumbers();
		for (int i = 0; i < NUMBER_OF_RANDOM_TESTS; i++) {
			int year = years.get(i);
			int month = months.get(i);
			int day = days.get(i);
			cal.set(year, month - 1, day); // months are counted 0..11
			int expected = cal.get(Calendar.DAY_OF_WEEK);

			// Sakamoto's algorithm returns Sunday as 0, Monday as 1, ...
			int actual = DayOfWeek.calculate(year, month, day) + 1;

			Assert.assertEquals(cal.getTime().toString(), expected, actual);
		}
	}
}
