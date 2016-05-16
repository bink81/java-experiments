package structure.trees;

import org.junit.Assert;
import org.junit.Test;

public class TernarySearchTriesSearchTest {

	private static final String DUMMY_KEY = "DUMMY_KEY";
	TernarySearchTriesSearch<Integer> search = new TernarySearchTriesSearch<Integer>();

	@Test
	public void testContainsWithoutPut() throws Exception {
		boolean actual = search.contains(DUMMY_KEY);

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testGetWithoutPut() throws Exception {
		Integer actual = search.get(DUMMY_KEY);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testContainsAfterPut() throws Exception {
		search.put(DUMMY_KEY, 1);

		boolean actual = search.contains(DUMMY_KEY);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testGetAfterPut() throws Exception {
		Integer expected = 1;
		search.put(DUMMY_KEY, expected);

		Integer actual = search.get(DUMMY_KEY);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDelete() throws Exception {
		search.delete(DUMMY_KEY);

		Integer actual = search.get(DUMMY_KEY);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testDeleteAfterPut() throws Exception {
		search.put(DUMMY_KEY, 1);
		search.delete(DUMMY_KEY);

		Integer actual = search.get(DUMMY_KEY);

		Assert.assertEquals(null, actual);
	}
}
