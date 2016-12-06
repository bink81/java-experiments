package advent2016;

import org.junit.Assert;
import org.junit.Test;

public class Day5Test {
	Day5 d = new Day5();

	@Test
	public void testTask11() throws Exception {
		String actual = d.task1("abc");

		Assert.assertEquals("18f47a30", actual);
	}

	@Test
	public void testTask12() throws Exception {
		String actual = d.task1("abbhdwsy");

		Assert.assertEquals("801b56a7", actual);
	}

	@Test
	public void testTask21() throws Exception {
		String actual = d.task2("abbhdwsy");

		Assert.assertEquals("[424a0197]", actual.replace(", ", ""));
	}
}
