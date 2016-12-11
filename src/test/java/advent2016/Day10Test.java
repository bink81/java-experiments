package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day10Test {

	@Test
	public void testTask1() throws Exception {
		long actual = new Day10().task2(fetchFile("day10.txt"));

		Assert.assertEquals(98, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day10().task1(fetchFile("day10.txt"));

		Assert.assertEquals(4042, actual);
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
