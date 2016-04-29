package utils;

import java.io.File;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PathUtilsTest {
	private static final String PART = "mail";

	private static final String SLASH = "/";

	private static final String ROOT = "https://mail.google.com";

	private static final String EXPECTED_WITHOUT_SLASH = ROOT + SLASH + PART;

	private static final String EXPECTED_WITH_SLASH = EXPECTED_WITHOUT_SLASH + SLASH;

	@Test
	public void testAssembleUrlWithOneParameter() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH);

		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneNull() throws Exception {
		String actual = PathUtils.assembleUrl((String) null);
		Assert.assertEquals(null, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndNull() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, null);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndTwoNull() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, null, null);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndEmptySpace() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, "");
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndTwoEmptySpaces() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, "", "");
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndSpace() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, " ");
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndSlash() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, SLASH);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterAndTwoSlashes() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH, SLASH, SLASH);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithOneParameterWithoutSlash() throws Exception {
		String actual = PathUtils.assembleUrl(EXPECTED_WITH_SLASH);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithTwoParameters() throws Exception {
		String actual = PathUtils.assembleUrl(ROOT, PART);
		Assert.assertEquals(EXPECTED_WITHOUT_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithSecondParameterWithSlashPrefix() throws Exception {
		String actual = PathUtils.assembleUrl(ROOT, SLASH + PART);
		Assert.assertEquals(EXPECTED_WITHOUT_SLASH, actual);
	}

	@Test
	public void testAssembleUrlWithSecondParameterWithSlashPrefixAndSuffix() throws Exception {
		String actual = PathUtils.assembleUrl(ROOT, SLASH + PART + SLASH);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);

	}

	@Test
	public void testAssembleUrlWithSecondParameterWithSlashSuffix() throws Exception {
		String actual = PathUtils.assembleUrl(ROOT, PART + SLASH);
		Assert.assertEquals(EXPECTED_WITH_SLASH, actual);
	}

	@Test
	public void testAssembleFilePathWithEmptyPart() throws Exception {
		String actual = PathUtils.assembleFilePath("");
		Assert.assertEquals("", actual);
	}

	@Test(expected = NullPointerException.class)
	public void testAssembleFilePathWithNullPart() throws Exception {
		PathUtils.assembleFilePath((String[]) null);
	}

	@Test
	public void testAssembleFilePathWithTwoParts() throws Exception {
		String actual = PathUtils.assembleFilePath(PART, PART);
		Assert.assertEquals(PART + File.separator + PART, actual);
	}
}
