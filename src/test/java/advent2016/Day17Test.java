package advent2016;

import org.junit.Assert;
import org.junit.Test;

public class Day17Test {

	@Test
	public void testTask21() throws Exception {
		String actual = new Day17().task2("gdjjyniy");

		Assert.assertEquals("578", actual);
	}

	@Test
	public void testTask22() throws Exception {
		String actual = new Day17().task2("ihgpwlah");

		Assert.assertEquals("370", actual);
	}

	@Test
	public void testTask23() throws Exception {
		String actual = new Day17().task2("kglvqrro");

		Assert.assertEquals("492", actual);
	}

	@Test
	public void testTask24() throws Exception {
		String actual = new Day17().task2("ulqzkmiv");

		Assert.assertEquals("830", actual);
	}

	@Test
	public void testTask1() throws Exception {
		String actual = new Day17().task1("gdjjyniy");

		Assert.assertEquals("DUDDRLRRRD", actual);
	}

	@Test
	public void testTest11() throws Exception {
		String actual = new Day17().task1("ihgpwlah");

		Assert.assertEquals("DDRRRD", actual);
	}

	@Test
	public void testTest12() throws Exception {
		String actual = new Day17().task1("kglvqrro");

		Assert.assertEquals("DDUDRLRRUDRD", actual);
	}

	@Test
	public void testTest13() throws Exception {
		String actual = new Day17().task1("ulqzkmiv");

		Assert.assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", actual);
	}

	@Test
	public void testCreateHash() throws Exception {
		String actual = new Day17().createHash("hijkl");

		Assert.assertEquals("ced9fc52441937264674bca3f4ba7588", actual);
	}

	@Test
	public void testIsAvailable0() throws Exception {
		Day17 d = new Day17();
		String singleHash = d.createHash("hijkl");
		boolean actual = d.isAvailable(0, 1, 0, singleHash);

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsAvailable1() throws Exception {
		Day17 d = new Day17();
		String singleHash = d.createHash("hijkl");
		boolean actual = d.isAvailable(1, 1, 2, singleHash);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsAvailable2() throws Exception {
		Day17 d = new Day17();
		String singleHash = d.createHash("hijkl");
		boolean actual = d.isAvailable(2, 0, 1, singleHash);

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsAvailable3() throws Exception {
		Day17 d = new Day17();
		String singleHash = d.createHash("hijkl");
		boolean actual = d.isAvailable(3, 1, 2, singleHash);

		Assert.assertEquals(false, actual);
	}
}
