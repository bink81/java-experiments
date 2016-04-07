package utils;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import wrappers.string.EmailAddressValidator;

public class EmailAddressValidatorTest {

	private EmailAddressValidator emailAddressValidator;

	@Before
	public void setUp() throws Exception {
		emailAddressValidator = new EmailAddressValidator();
	}

	@Test
	public void testValidNames() throws Exception {
		Assert.assertTrue(emailAddressValidator.isValid("foo@gmail.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo@co.uk"));
		Assert.assertTrue(emailAddressValidator.isValid("foo@yahoo.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo-foo@yahoo.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo.foo@yahoo.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo-foo@yahoo.net"));
		Assert.assertTrue(emailAddressValidator.isValid("foo-foo@foo.com.au"));
		Assert.assertTrue(emailAddressValidator.isValid("foo@1.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo+1@1.com"));
		Assert.assertTrue(emailAddressValidator.isValid("foo-1@foo-test.com"));
	}

	@Test
	public void testInvalidNames() throws Exception {
		Assert.assertFalse(emailAddressValidator.isValid("foo@.com.my"));
		Assert.assertFalse(emailAddressValidator.isValid("foo@.com"));
		Assert.assertFalse(emailAddressValidator.isValid("foo@.com.com"));
		Assert.assertFalse(emailAddressValidator.isValid("foo@@foo.com"));
		Assert.assertFalse(emailAddressValidator.isValid("@foo.com"));
		Assert.assertFalse(emailAddressValidator.isValid("foo@foo@foo.com"));
		Assert.assertFalse(emailAddressValidator.isValid("foo@foo.com.1a"));
	}
}
