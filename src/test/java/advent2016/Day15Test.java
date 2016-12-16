package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day15Test {

	@Test
	public void testTask1() throws Exception {
		long actual = new Day15().task1(fetchFile("day15-1.txt"));

		Assert.assertEquals(497590, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day15().task1(fetchFile("day15-2.txt"));

		Assert.assertEquals(2408135, actual);
	}

	@Test
	public void testTask2example() throws Exception {
		long actual = new Day15().task1(fetchFile("day15-test.txt"));

		Assert.assertEquals(5, actual);
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
