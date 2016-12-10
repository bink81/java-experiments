package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day7Test {

	@Test
	public void testIsAbba() throws Exception {
		boolean actual = new Day7().isAbba("abba", 0);

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testSupportsTls1() throws Exception {
		boolean actual = new Day7().supportsTls("abba[mnop]qrst");

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testSupportsTls2() throws Exception {
		boolean actual = new Day7().supportsTls("abcd[bddb]xyyx");

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testSupportsTls3() throws Exception {
		boolean actual = new Day7().supportsTls("aaaa[qwer]tyui");

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testSupportsTls4() throws Exception {
		boolean actual = new Day7().supportsTls("ioxxoj[asdfgh]zxcvbn");

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testSupportsTls5() throws Exception {
		boolean actual = new Day7()
				.supportsTls("bpjaenogjpzaxxkejpa[jqgajznyesxfcdhph]euziwygknckkkkunxux");

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testSupportsTlsTest() throws Exception {
		long actual = new Day7().task1(fetchFile("day7-test.txt"));

		Assert.assertEquals(118, actual);
	}

	@Test
	public void testSupportsTls21() throws Exception {
		boolean actual = new Day7().supportsSsl("aba[bab]xyz");

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testSupportsTls22() throws Exception {
		boolean actual = new Day7().supportsSsl("xyx[xyx]xyx");

		Assert.assertEquals(false, actual);
	}

	@Test
	public void testSupportsTls23() throws Exception {
		boolean actual = new Day7().supportsSsl("aaa[kek]eke");

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testSupportsTls24() throws Exception {
		boolean actual = new Day7().supportsSsl("zazbz[bzb]cdb");

		Assert.assertEquals(true, actual);
	}

	@Test
	public void testTask1() throws Exception {
		long actual = new Day7().task2(fetchFile("day7-input.txt"));

		Assert.assertEquals(260, actual);
	}

	private File fetchFile(String filename) throws Exception {
		try {
			String filePath = PathUtils.assembleFilePath("src", "test", "java", "advent2016", filename);
			return new File(filePath);
		} catch (URISyntaxException e) {
			throw new Exception();
		}
	}
}