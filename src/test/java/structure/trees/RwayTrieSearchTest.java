package structure.trees;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class RwayTrieSearchTest {
	private static final String DUMMY_KEY_1 = "DUMMY_KEY_1";
	private static final String DUMMY_KEY_2 = "DUMMY_KEY_2";
	private RwayTrieSearch<Integer> search = new RwayTrieSearch<Integer>();

	@Test
	public void testContainsWithoutPut() throws Exception {
		boolean actual = search.contains(DUMMY_KEY_1);

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testGetWithoutPut() throws Exception {
		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testGetWithoutCorrectPut() throws Exception {
		search.put(DUMMY_KEY_2, 1);

		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testContainsAfterPutOne() throws Exception {
		search.put(DUMMY_KEY_1, 1);

		boolean actual = search.contains(DUMMY_KEY_1);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testContainsAfterPutTwo() throws Exception {
		search.put(DUMMY_KEY_1, 1);
		search.put(DUMMY_KEY_2, 1);

		boolean actual = search.contains(DUMMY_KEY_1);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testGetAfterPut() throws Exception {
		Integer expected = 1;
		search.put(DUMMY_KEY_1, expected);
		search.put(DUMMY_KEY_2, expected);

		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetAfterOverwritePut() throws Exception {
		Integer expected = 1;
		search.put(DUMMY_KEY_1, 0);
		search.put(DUMMY_KEY_1, expected);

		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDelete() throws Exception {
		search.delete(DUMMY_KEY_1);

		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testDeleteAfterPut() throws Exception {
		search.put(DUMMY_KEY_1, 1);
		search.delete(DUMMY_KEY_1);

		Integer actual = search.get(DUMMY_KEY_1);

		Assert.assertEquals(null, actual);
	}

	@Test
	public void testKeysWithEmpty() throws Exception {
		Iterable<String> actual = search.keys();

		Assert.assertEquals(false, actual.iterator().hasNext());
	}

	@Test
	public void testKeysWithOne() throws Exception {
		search.put(DUMMY_KEY_1, 1);

		Iterable<String> actual = search.keys();

		Assert.assertEquals(DUMMY_KEY_1, actual.iterator().next());
	}

	@Test
	public void testKeysWithTwo() throws Exception {
		search.put(DUMMY_KEY_1, 1);
		search.put(DUMMY_KEY_2, 1);

		Iterable<String> actual = search.keys();

		Iterator<String> iterator = actual.iterator();
		Assert.assertEquals(DUMMY_KEY_1, iterator.next());
		Assert.assertEquals(DUMMY_KEY_2, iterator.next());
	}

	@Test
	public void testKeysWithPrefixWithEmpty() throws Exception {
		Iterable<String> actual = search.keysWithPrefix("DUMMY_KEY_");

		Assert.assertEquals(false, actual.iterator().hasNext());
	}

	@Test
	public void testKeysWithPrefixWithOne() throws Exception {
		search.put(DUMMY_KEY_1, 1);

		Iterable<String> actual = search.keysWithPrefix("DUMMY_KEY_");

		Assert.assertEquals(DUMMY_KEY_1, actual.iterator().next());
	}

	@Test
	public void testKeysWithPrefix() throws Exception {
		search.put(DUMMY_KEY_1, 1);
		search.put(DUMMY_KEY_2, 1);

		Iterable<String> actual = search.keysWithPrefix("DUMMY_KEY_");

		Iterator<String> iterator = actual.iterator();
		Assert.assertEquals(DUMMY_KEY_1, iterator.next());
		Assert.assertEquals(DUMMY_KEY_2, iterator.next());
	}
}
