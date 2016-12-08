package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day3Test {

	@Test
	public void testTask1() throws Exception {
		long actual = new Day3().task1(fetchFile());

		Assert.assertEquals(869, actual);
	}

	@Test
	public void testTask2() throws Exception {
		int actual = new Day3().task2(fetchFile());

		Assert.assertEquals(1544, actual);
	}

	private File fetchFile() throws Exception {
		try {
			String filePath = PathUtils
				.assembleFilePath("src", "test", "java", "advent2016", "day3-input.txt");
			return new File(filePath);
		} catch (URISyntaxException e) {
			throw new Exception();
		}
	}
}
