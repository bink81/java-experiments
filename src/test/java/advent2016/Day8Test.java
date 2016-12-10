package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day8Test {

	@Test
	public void testTask1() throws Exception {
		long actual = new Day8().task1(fetchFile("day8-input.txt"));

		Assert.assertEquals(116, actual);
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
