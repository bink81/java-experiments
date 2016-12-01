package advent2016;

import org.junit.Assert;
import org.junit.Test;

import advent2016.Direction;

public class DirectionTest {

	@Test
	public void testPreviousNorth() throws Exception {
		Direction d = Direction.EAST;

		Assert.assertEquals(Direction.NORTH, d.previous());
	}

	@Test
	public void testPreviousWest() throws Exception {
		Direction d = Direction.NORTH;

		Assert.assertEquals(Direction.WEST, d.previous());
	}

	@Test
	public void testNextNorth() throws Exception {
		Direction d = Direction.WEST;

		Assert.assertEquals(Direction.NORTH, d.next());
	}

	@Test
	public void testNextWest() throws Exception {
		Direction d = Direction.SOUTH;

		Assert.assertEquals(Direction.WEST, d.next());
	}

}
