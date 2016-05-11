package translations;

import org.junit.Assert;
import org.junit.Test;

public class TranslatableTest implements Translatable {

	private static final String NAME = "name";

	@Override
	public String getBundleName() {
		return NAME;
	}

	@Test
	public void testGetBundleName() throws Exception {
		Assert.assertEquals(NAME, getBundleName());
	}
}
