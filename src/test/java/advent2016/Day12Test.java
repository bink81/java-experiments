package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day12Test {

	@Test
	public void testTask11() throws Exception {
		long actual = new Day12().task1(fetchFile("day12-test1.txt"));

		Assert.assertEquals(42, actual);
	}

	@Test
	public void testTask1() throws Exception {
		long actual = new Day12().task1(fetchFile("day12.txt"));

		Assert.assertEquals(317993, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day12(1).task1(fetchFile("day12.txt"));

		Assert.assertEquals(9227647, actual);
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
