package advent2016;

import org.junit.Assert;
import org.junit.Test;

public class Day18Test {

	@Test
	public void testGenerateNextRow1() throws Exception {
		String actual = new Day18().generateNextRow("..^^.");

		Assert.assertEquals(".^^^^", actual);
	}

	@Test
	public void testGenerateNextRow2() throws Exception {
		String actual = new Day18().generateNextRow(".^^^^");

		Assert.assertEquals("^^..^", actual);
	}

	@Test
	public void testTask11() throws Exception {
		long actual = new Day18().task1(".^^.^.^^^^", 10);

		Assert.assertEquals(38, actual);
	}

	@Test
	public void testTask1() throws Exception {
		long actual = new Day18().task1(
				".^^^^^.^^.^^^.^...^..^^.^.^..^^^^^^^^^^..^...^^.^..^^^^..^^^^...^.^.^^^^^^^^....^..^^^^^^.^^^.^^^.^^",
				40);

		Assert.assertEquals(1989, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day18().task1(
				".^^^^^.^^.^^^.^...^..^^.^.^..^^^^^^^^^^..^...^^.^..^^^^..^^^^...^.^.^^^^^^^^....^..^^^^^^.^^^.^^^.^^",
				400000);

		Assert.assertEquals(19999894, actual);
	}
}
