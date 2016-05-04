package wrappers.string;

import org.junit.Assert;
import org.junit.Test;

import wrappers.core.SecretStringWrapper;

public class PasswordTest {
	private static final String TOO_SHORT_PASSWORD = "dummy";

	private static final String NO_LOWERCASES_PASSWORD = "12345678";

	private static final String VALID_PASSWORD = "dummydummy";

	@Test(expected = IllegalArgumentException.class)
	public void testTooShort() throws Exception {
		Password.of(TOO_SHORT_PASSWORD);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoLowercases() throws Exception {
		Password.of(NO_LOWERCASES_PASSWORD);
	}

	@Test
	public void testValid() throws Exception {
		Password password = Password.of(VALID_PASSWORD);

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
