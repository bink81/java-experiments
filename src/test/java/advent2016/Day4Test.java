package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day4Test {

	Day4 d = new Day4();

	@Test
	public void testIsReal1() throws Exception {
		boolean real = d.isReal("aaaaa-bbb-z-y-x-123[abxyz]");

		Assert.assertEquals(true, real);
	}

	@Test
	public void testIsReal2() throws Exception {
		boolean real = d.isReal("a-b-c-d-e-f-g-h-987[abcde]");

		Assert.assertEquals(true, real);
	}

	@Test
	public void testIsReal3() throws Exception {
		boolean real = d.isReal("not-a-real-room-404[oarel]");

		Assert.assertEquals(true, real);
	}

	@Test
	public void testIsReal4() throws Exception {
		boolean real = d.isReal("totally-real-room-200[decoy]");

		Assert.assertEquals(false, real);
	}

	@Test
	public void testTask1() throws Exception {
		long actual = d.task1(fetchFile());

		Assert.assertEquals(409147, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = d.task2(fetchFile());

		Assert.assertEquals(991, actual);
	}

	private File fetchFile() throws Exception {
		try {
			String filePath = PathUtils.assembleFilePath("src", "test", "java", "advent2016",
					"day4-input.txt");
			return new File(filePath);
		} catch (URISyntaxException e) {
			throw new Exception();
		}
	}

	@Test
	public void testDecryptRoomName() throws Exception {
		String actual = d.decryptRoomName("qzmt-zixmtkozy-ivhz-343");

		Assert.assertEquals("very encrypted name", actual);
	}
}
