package advent2016;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import utils.PathUtils;

public class Day9Test {
	//
	// @Test
	// public void testTask11() throws Exception {
	// long actual = new Day9().decompress1("ADVENT");
	//
	// Assert.assertEquals("ADVENT".length(), actual);
	// }
	//
	// @Test
	// public void testTask12() throws Exception {
	// long actual = new Day9().decompress1("A(1x5)BC");
	//
	// Assert.assertEquals("ABBBBBC".length(), actual);
	// }
	//
	// @Test
	// public void testTask13() throws Exception {
	// long actual = new Day9().decompress1("(3x3)XYZ");
	//
	// Assert.assertEquals("XYZXYZXYZ".length(), actual);
	// }
	//
	// @Test
	// public void testTask14() throws Exception {
	// long actual = new Day9().decompress1("A(2x2)BCD(2x2)EFG");
	//
	// Assert.assertEquals("ABCBCDEFEFG".length(), actual);
	// }
	//
	// @Test
	// public void testTask15() throws Exception {
	// long actual = new Day9().decompress1("(6x1)(1x3)A");
	//
	// Assert.assertEquals("(1x3)A".length(), actual);
	// }
	//
	// @Test
	// public void testTask16() throws Exception {
	// long actual = new Day9().decompress1("X(8x2)(3x3)ABCY");
	//
	// Assert.assertEquals("X(3x3)ABC(3x3)ABCY".length(), actual);
	// }
	//
	// @Test
	// public void testTask1() throws Exception {
	// long actual = new Day9().task1(fetchFile("day9.txt"));
	//
	// Assert.assertEquals(123908, actual);
	// }

	@Test
	public void testTask21() throws Exception {
		long actual = new Day9().decompress2("(3x3)XYZ");

		Assert.assertEquals("XYZXYZXYZ".length(), actual);
	}

	@Test
	public void testTask22() throws Exception {
		long actual = new Day9().decompress2("X(8x2)(3x3)ABCY");

		Assert.assertEquals("XABCABCABCABCABCABCY".length(), actual);
	}

	@Test
	public void testTask23() throws Exception {
		long actual = new Day9().decompress2("(27x12)(20x12)(13x14)(7x10)(1x12)A");

		Assert.assertEquals(241920, actual);
	}

	@Test
	public void testTask24() throws Exception {
		long actual = new Day9()
				.decompress2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN");

		Assert.assertEquals(445, actual);
	}

	@Test
	public void testTask2() throws Exception {
		long actual = new Day9().task2(fetchFile("day9.txt"));

		Assert.assertEquals(10755693147L, actual);
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
