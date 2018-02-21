package math;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Preconditions;

public class TriangleTest {

	private static final BigDecimal MINUS_ONE = new BigDecimal("-1");
	private static final BigDecimal ZERO = BigDecimal.ZERO;
	private static final BigDecimal ONE = BigDecimal.ONE;
	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal THREE = new BigDecimal("3");
	private static final BigDecimal FOUR = new BigDecimal("4");
	private static final BigDecimal FIVE = new BigDecimal("5.000");
	private static final BigDecimal TEN = BigDecimal.TEN;

	// 1. Check null parameters
	@Test(expected = NullPointerException.class)
	public void givenSideANullThenFailConstruction() throws Exception {
		new Triangle(null, ONE, ONE);
	}

	@Test(expected = NullPointerException.class)
	public void givenSideBNullThenFailConstruction() throws Exception {
		new Triangle(ONE, null, ONE);
	}

	@Test(expected = NullPointerException.class)
	public void givenSideCNullThenFailConstruction() throws Exception {
		new Triangle(ONE, ONE, null);
	}

	// 2. Check zero parameters
	@Test(expected = IllegalArgumentException.class)
	public void givenSideAZeroThenFailConstruction() throws Exception {
		new Triangle(ZERO, ONE, ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSideBZeroThenFailConstruction() throws Exception {
		new Triangle(ONE, ZERO, ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSideCZeroThenFailConstruction() throws Exception {
		new Triangle(ONE, ONE, ZERO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAllSidesZeroThenFailConstruction() throws Exception {
		new Triangle(ZERO, ZERO, ZERO);
	}

	// 3. Check negative parameters
	@Test(expected = IllegalArgumentException.class)
	public void givenSideANegativeThenFailConstruction() throws Exception {
		new Triangle(invertSign(ONE), ONE, ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSideBNegativeThenFailConstruction() throws Exception {
		new Triangle(ONE, invertSign(ONE), ONE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSideCNegativeThenFailConstruction() throws Exception {
		new Triangle(ONE, ONE, invertSign(ONE));
	}

	private BigDecimal invertSign(final BigDecimal value) {
		Preconditions.checkNotNull(value, "value must not be null!");
		return value.multiply(MINUS_ONE);
	}

	// 4. Check inequality
	@Test(expected = TriangleUnequalException.class)
	public void givenTooBigSideAThenFailConstruction() throws Exception {
		new Triangle(TEN, ONE, ONE);
	}

	@Test(expected = TriangleUnequalException.class)
	public void givenTooBigSideBThenFailConstruction() throws Exception {
		new Triangle(ONE, TEN, ONE);
	}

	@Test(expected = TriangleUnequalException.class)
	public void givenTooBigSideCThenFailConstruction() throws Exception {
		new Triangle(ONE, ONE, TEN);
	}

	// 5. Check invalid triangle (straight line)
	@Test(expected = TriangleUnequalException.class)
	public void givenWeirdSides1ThenFailConstruction() throws Exception {
		new Triangle(TEN, FIVE, FIVE);
	}

	@Test(expected = TriangleUnequalException.class)
	public void givenWeirdSides2ThenFailConstruction() throws Exception {
		new Triangle(FIVE, TEN, FIVE);
	}

	@Test(expected = TriangleUnequalException.class)
	public void givenWeirdSides3ThenFailConstruction() throws Exception {
		new Triangle(FIVE, FIVE, TEN);
	}

	// 5. Check method determineType()
	@Test
	public void givenUnequalSidesThenTypeIsScalene() throws Exception {
		Assert.assertEquals(Triangle.Type.SCALENE, new Triangle(THREE, FOUR, FIVE).determineType());
		Assert.assertEquals(Triangle.Type.SCALENE,
				new Triangle(ONE, new BigDecimal("1.0000000000000000000000000000001"), TWO).determineType());
		Assert.assertEquals(Triangle.Type.SCALENE, new Triangle(new BigDecimal("91E+1000000000"),
				new BigDecimal("92.4E+1000000000"), new BigDecimal("93.2E+1000000000")).determineType());
	}

	@Test
	public void givenEqualAllSidesThenTypeIsScalene() throws Exception {
		Assert.assertEquals(Triangle.Type.EQUILATERAL, new Triangle(THREE, THREE, THREE).determineType());
		Assert.assertEquals(Triangle.Type.EQUILATERAL,
				new Triangle(new BigDecimal("2.0"), new BigDecimal("2.000"), TWO).determineType());
	}

	@Test
	public void givenEqualTwoSidesThenTypeIsIsosceles() throws Exception {
		Assert.assertEquals(Triangle.Type.ISOSCELES, new Triangle(THREE, THREE, FOUR).determineType());
		Assert.assertEquals(Triangle.Type.ISOSCELES, new Triangle(THREE, FOUR, THREE).determineType());
		Assert.assertEquals(Triangle.Type.ISOSCELES, new Triangle(FOUR, THREE, THREE).determineType());
		Assert.assertEquals(Triangle.Type.ISOSCELES, new Triangle(THREE, new BigDecimal("2.000"), TWO).determineType());
	}
}
