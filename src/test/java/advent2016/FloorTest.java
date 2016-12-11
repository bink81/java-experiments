package advent2016;

import org.junit.Assert;
import org.junit.Test;

public class FloorTest {

	@Test
	public void testIsValid1() throws Exception {
		Floor floor = new Floor();

		Assert.assertEquals(true, floor.valid());
	}

	@Test
	public void testIsValid2() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");

		Assert.assertEquals(true, floor.valid());
	}

	@Test
	public void testIsValid3() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");
		floor.addGenerator("b");

		Assert.assertEquals(true, floor.valid());
	}

	@Test
	public void testIsValid4() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");
		floor.addMicrochip("a");

		Assert.assertEquals(true, floor.valid());
	}

	@Test
	public void testIsValid5() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");
		floor.addMicrochip("a");
		floor.addGenerator("b");

		Assert.assertEquals(true, floor.valid());
	}

	@Test
	public void testIsInvalid1() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");
		floor.addMicrochip("b");

		Assert.assertEquals(false, floor.valid());
	}

	@Test
	public void testIsInvalid2() throws Exception {
		Floor floor = new Floor();
		floor.addGenerator("a");
		floor.addGenerator("b");
		floor.addMicrochip("c");

		Assert.assertEquals(false, floor.valid());
	}
}
