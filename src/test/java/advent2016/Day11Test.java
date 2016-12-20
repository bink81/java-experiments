package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day11Test {

	// @Test
	// public void testTask11() throws Exception {
	// long actual = new Day11().task1(fetchFile("day11-test1.txt"));
	//
	// Assert.assertEquals(11, actual);
	// }

	@Test
	public void testTask1() throws Exception {
		long actual = new Day11().task1(fetchFile("day11.txt"));

		Assert.assertEquals(47, actual);
	}

	private File fetchFile(String filename) throws Exception {
		try {
			String filePath =
					PathUtils.assembleFilePath("src", "test", "java", "advent2016", filename);
			return new File(filePath);
		} catch (URISyntaxException e) {
			throw new Exception();
		}
	}
}
