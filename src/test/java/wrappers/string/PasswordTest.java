package wrappers.string;

import org.junit.Test;

import junit.framework.Assert;
import wrappers.core.SecretStringWrapper;

public class PasswordTest {
	@Test
	public void testToString() throws Exception {
		Password password = Password.of("dummy");

		Assert.assertEquals(SecretStringWrapper.REPLACEMENT_TEXT, password.toString());
	}
}
