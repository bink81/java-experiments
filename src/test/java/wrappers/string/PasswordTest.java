package wrappers.string;

import org.junit.Test;

import junit.framework.Assert;

public class PasswordTest {
	@Test
	public void testToString() throws Exception {
		Password password = Password.of("dummy");

		Assert.assertEquals(Password.DISPLAYED_TEXT, password.toString());
	}
}
