package code.validation;

import org.junit.Test;

import junit.framework.Assert;

public class CollectionSafeClassTest {
	private static final Long ID = 1L;

	@Test
	public void testCreation() throws Exception {
		CollectionSafeClass collectionSafeClass = new CollectionSafeClass(ID);

		Assert.assertEquals(ID, collectionSafeClass.getId());
	}
}
