package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day10Test {
	private static final int FINAL_LOW = 17;
	private static final int FINAL_HIGH = 61;

	@Test
	public void testTask211() throws Exception {
		long actual = new Day10(2, 5).task1(fetchFile("day10-test1.txt"));

		Assert.assertEquals(2, actual);
	}

	@Test
	public void testTask21() throws Exception {
		long actual = new Day10(FINAL_LOW, FINAL_HIGH).task1(fetchFile("day10.txt"));

		Assert.assertEquals(98, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day10(FINAL_LOW, FINAL_HIGH).task2(fetchFile("day10.txt"));

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
