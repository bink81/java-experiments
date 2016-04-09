package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class CollectionToListConverterTest {

	private static final String DUMMY_VALUE = "DUMMY_VALUE";

	@Test(expected = NullPointerException.class)
	public void testNull() throws Exception {
		new CollectionToListConverter<String>(null).convert();
	}

	@Test
	public void testEmpty() throws Exception {
		List<String> list = new CollectionToListConverter<String>(Collections.emptyList()).convert();

		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testOneElement() throws Exception {
		Collection<String> collection = new HashSet<>();
		collection.add(DUMMY_VALUE);
		List<String> list = new CollectionToListConverter<String>(collection).convert();

		Assert.assertEquals(1, list.size());
		Assert.assertEquals(DUMMY_VALUE, list.get(0));
	}

}
