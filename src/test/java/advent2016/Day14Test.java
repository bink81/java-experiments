package advent2016;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Day14Test {

	@Test
	public void testGetIndex4() throws Exception {
		long actual = new Day14().getIndex2("abc", 64);

		Assert.assertEquals(22097, actual);
	}

	@Test
	public void testCreateHash() throws Exception {
		String actual = new Day14().createHash("cuanljph", 20605, true);

		Assert.assertEquals("10ed8a2072f1438d70c00e7d531fc865", actual);
	}

	@Test
	public void testCreateHash0() throws Exception {
		String actual = new Day14().createHash("abc", 0, true);

		Assert.assertEquals("a107ff634856bb300138cac6568c0f24", actual);
	}

	@Test
	public void testGetIndex22() throws Exception {
		long actual = new Day14().getIndex2("abc", 1);

		Assert.assertEquals(10, actual);
	}

	@Test
	public void testGetIndex64() throws Exception {
		long actual = new Day14().getIndex1("abc", 64);

		Assert.assertEquals(21908, actual);
	}

	@Test
	public void testGetIndex2() throws Exception {
		long actual = new Day14().getIndex1("abc", 2);

		Assert.assertEquals(92, actual);
	}

	@Test
	public void testGetIndex1() throws Exception {
		long actual = new Day14().getIndex1("abc", 1);

		Assert.assertEquals(39, actual);
	}

	@Test
	public void testGetSequence1() throws Exception {
		List<Integer> actual = new Day14().getSequence("a", 3);

		Assert.assertEquals(0, actual.size());
	}

	@Test
	public void testGetSequence2() throws Exception {
		List<Integer> actual = new Day14().getSequence("aa", 3);

		Assert.assertEquals(0, actual.size());
	}

	@Test
	public void testGetSequence3() throws Exception {
		List<Integer> actual = new Day14().getSequence("aaa", 3);

		Assert.assertEquals(1, actual.size());
	}

	@Test
	public void testGetSequence4() throws Exception {
		List<Integer> actual = new Day14().getSequence("aaacbbb", 3);

		Assert.assertEquals(2, actual.size());
	}

	@Test
	public void testGetSequence51() throws Exception {
		List<Integer> actual = new Day14().getSequence("aaaaaacbbb", 3);

		Assert.assertEquals(2, actual.size());
	}

	@Test
	public void testGetSequence52() throws Exception {
		List<Integer> actual = new Day14().getSequence("aaaaaacbbbbb", 5);

		Assert.assertEquals(2, actual.size());
	}

	@Test
	public void testGetSequence53() throws Exception {
		List<Integer> actual = new Day14().getSequence("aaaaaacbbb", 5);

		Assert.assertEquals(1, actual.size());
	}

	@Test
	public void testGetSequence54() throws Exception {
		List<Integer> actual = new Day14().getSequence("10ed8a2072f1438d70c00e7d531fc865", 3);

		Assert.assertEquals(0, actual.size());
	}
}
