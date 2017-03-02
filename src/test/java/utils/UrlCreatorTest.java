package utils;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;

public class UrlCreatorTest {

	private static final String WWW_GOOGLE_COM = "www.google.com";
	private static final String VALID_URL = "https://" + WWW_GOOGLE_COM;

	@Test(expected = NullPointerException.class)
	public void testNormalizeNull() throws Exception {
		new UrlCreator(null).normalize();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNormalizeEmpty() throws Exception {
		new UrlCreator("").normalize();
	}

	@Test(expected = MalformedURLException.class)
	public void testNormalizeMalformedA() throws Exception {
		new UrlCreator("A").normalize();
	}

	@Test(expected = MalformedURLException.class)
	public void testNormalizeMalformed() throws Exception {
		String path = WWW_GOOGLE_COM;

		String actual = new UrlCreator(path).normalize();

		Assert.assertEquals(path, actual);
	}

	public void testNormalizeOK() throws Exception {

		String actual = new UrlCreator(VALID_URL).normalize();

		Assert.assertEquals(VALID_URL, actual);
	}

	public void testNormalizeOKWithSlash() throws Exception {

		String actual = new UrlCreator(VALID_URL + "/").normalize();

		Assert.assertEquals(VALID_URL, actual);
	}

	public void testNormalizeOKWithBackslash() throws Exception {
		String actual = new UrlCreator(VALID_URL + "\\").normalize();

		Assert.assertEquals(VALID_URL, actual);
	}
}
