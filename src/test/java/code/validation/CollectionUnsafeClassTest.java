package code.validation;

import org.junit.Test;

import junit.framework.Assert;

public class CollectionUnsafeClassTest {
	private static final Long ID = 1L;

	@Test
	public void testCreation() throws Exception {
		CollectionUnsafeClass collectionUnsafeClass = new CollectionUnsafeClass(ID);

		Assert.assertEquals(ID, collectionUnsafeClass.getId());
	}
}