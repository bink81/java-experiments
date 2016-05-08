package algorithms.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class StringDistanceTest {

	@Test
	public void testDistance() throws Exception {
		int actual = StringDistance.calculateDistance("bread", "really");

		Assert.assertEquals(4, actual);
	}

	@Test
	public void test0() throws Exception {
		int actual = StringDistance.calculateDistance("ab", "ab");

		Assert.assertEquals(0, actual);
	}

	@Test
	public void test3() throws Exception {
		int actual = StringDistance.calculateDistance("short", "ports");

		Assert.assertEquals(3, actual);
	}

	@Test
	public void test5() throws Exception {
		int actual = StringDistance.calculateDistance("editing", "distance");

		Assert.assertEquals(5, actual);
	}
}
