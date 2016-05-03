package streams;

import org.junit.Assert;
import org.junit.Test;

public class StreamsWithPredicatesTest {

	@Test
	public void testCountEmptyStrings() throws Exception {
		String[] strings = new String[] { "", "", "" };
		long actual = StreamsWithPredicates.countEmptyStrings(strings);

		Assert.assertEquals(strings.length, actual);
	}

	@Test
	public void testCountStrings() throws Exception {
		String[] strings = new String[] { "A", "", "B" };
		long actual = StreamsWithPredicates.countEmptyStrings(strings);

		Assert.assertEquals(1, actual);
	}

	@Test
	public void testCountNonEmptyStrings() throws Exception {
		String[] strings = new String[] { "A", "", "B" };
		long actual = StreamsWithPredicates.countNonEmptyStrings(strings);

		Assert.assertEquals(2, actual);
	}
}
