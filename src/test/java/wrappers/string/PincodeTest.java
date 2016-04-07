package wrappers.string;

import org.junit.Test;

import junit.framework.Assert;
import wrappers.core.SecretStringWrapper;

public class PincodeTest {
	@Test
	public void testToString() throws Exception {
		Pincode pincode = Pincode.of("1234");

		Assert.assertEquals(SecretStringWrapper.REPLACEMENT_TEXT, pincode.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyValue() throws Exception {
		Pincode.of("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		Pincode.of(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMaxLengthExceeded() throws Exception {
		Pincode.of("123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnderscore() throws Exception {
		Pincode.of("_");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnderscoreWithDigits() throws Exception {
		Pincode.of("1_2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotDigits() throws Exception {
		Pincode.of("text");
	}
}
