package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day6Test {

	@Test
	public void testTask1example() throws Exception {
		String actual = new Day6().task1(fetchFile("day6-test.txt"));

		Assert.assertEquals("easter", actual);
	}

	@Test
	public void testTask1() throws Exception {
		String actual = new Day6().task1(fetchFile("day6-input.txt"));

		Assert.assertEquals("umejzgdw", actual);
	}

	@Test
	public void testTask2example() throws Exception {
		String actual = new Day6().task2(fetchFile("day6-test.txt"));

		Assert.assertEquals("advent", actual);
	}

	@Test
	public void testTask2() throws Exception {
		String actual = new Day6().task2(fetchFile("day6-input.txt"));

		Assert.assertEquals("aovueakv", actual);
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
