package wrappers.date;

import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;

public class PastDateTest {
	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		PastDate.of(null);
	}

	@Test
	public void testPastDate() throws Exception {
		Date pastDate = new Date(System.currentTimeMillis() - 1000);

		Assert.assertEquals(pastDate, PastDate.of(pastDate).get());
	}

	@Test(expected = DateNotInPastException.class)
	public void testFutureDate() throws Exception {
		Date futureDate = new Date(System.currentTimeMillis() + 1000);

		Assert.assertEquals(futureDate, PastDate.of(futureDate).get());
	}
}
