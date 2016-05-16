package structure.trees;

import org.junit.Assert;
import org.junit.Test;

public class TernarySearchTriesSearchTest {

	private static final String DUMMY_KEY = "DUMMY_KEY";

	@Test
	public void testContainsWithoutPut() throws Exception {
		TernarySearchTriesSearch<Integer> s = new TernarySearchTriesSearch<Integer>();

		boolean actual = s.contains(DUMMY_KEY);

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testGetWithoutPut() throws Exception {
		TernarySearchTriesSearch<Integer> s = new TernarySearchTriesSearch<Integer>();

		Integer actual = s.get(DUMMY_KEY);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testContainsAfterPut() throws Exception {
		TernarySearchTriesSearch<Integer> s = new TernarySearchTriesSearch<Integer>();
		s.put(DUMMY_KEY, 1);

		boolean actual = s.contains(DUMMY_KEY);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testGetAfterPut() throws Exception {
		TernarySearchTriesSearch<Integer> s = new TernarySearchTriesSearch<Integer>();
		Integer expected = 1;
		s.put(DUMMY_KEY, expected);

		Integer actual = s.get(DUMMY_KEY);

		Assert.assertEquals(expected, actual);
	}
}
