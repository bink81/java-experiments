package utils;

import org.junit.Test;

import junit.framework.Assert;

public class DayOfWeekTest {
	@Test
	public void test() throws Exception {
		int actual = DayOfWeek.calculate(2016, 4, 22);

		Assert.assertEquals(5, actual);
	}
}
