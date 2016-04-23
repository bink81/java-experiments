package wrappers.string;

import org.junit.Test;

import org.junit.Assert;
import wrappers.core.SecretStringWrapper;

public class PasswordTest {
	@Test
	public void testToString() throws Exception {
		Password password = Password.of("dummy");

		Assert.assertEquals(SecretStringWrapper.REPLACEMENT_TEXT, password.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyValue() throws Exception {
		Password.of("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Password.of(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMaxLengthExceeded() throws Exception {
		Password.of( // 130 characters
			"1234567890" + "1234567890" + "1234567890" + "1234567890" + "1234567890" + "1234567890"
					+ "1234567890" + "1234567890" + "1234567890" + "1234567890" + "1234567890"
					+ "1234567890" + "1234567890");
	}

}
