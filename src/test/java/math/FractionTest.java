package math;

import org.junit.Test;

import org.junit.Assert;

public class FractionTest {

	@Test
	public void testEquals() throws Exception {
		Assert.assertEquals(newFractionOneHalf(), newFractionOneHalf());
	}

	@Test
	public void testEqualsFractionsWithSign() throws Exception {
		Assert.assertEquals(new Fraction(-1, 2), new Fraction(1, -2));
	}

	@Test
	public void testEqualsWholesWithSign() throws Exception {
		Assert.assertEquals(new Fraction(-1), new Fraction(-1));
	}

	@Test
	public void testEqualsWithOperations() throws Exception {
		Assert.assertEquals(newFractionOne(), new Fraction(1, 3).multiply(new Fraction(3)));
	}

	@Test
	public void testEqualsWithNull() throws Exception {
		Assert.assertTrue(!newFractionOne().equals(null));
	}

	@Test
	public void testAdd() throws Exception {
		Assert.assertEquals(newFractionOne(), newFractionOneHalf().add(newFractionOneHalf()));
	}

	@Test
	public void testAddWithOperations() throws Exception {
		Assert.assertEquals(new Fraction(3, 2), newFractionOneHalf().add(newFractionOneHalf()).add(newFractionOneHalf()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddWithNull() throws Exception {
		Assert.assertEquals(null, newFractionOneHalf().add(null));
	}

	@Test
	public void testSubtract() throws Exception {
		Assert.assertEquals(new Fraction(0), newFractionOneHalf().subtract(newFractionOneHalf()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubtractWithNull() throws Exception {
		Assert.assertEquals(null, newFractionOneHalf().add(null));
	}

	@Test
	public void testDivide() throws Exception {
		Assert.assertEquals(newFractionOne(), newFractionOneHalf().divide(newFractionOneHalf()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDivideWithNull() throws Exception {
		Assert.assertEquals(null, newFractionOneHalf().divide(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDivideByZero() throws Exception {
		Assert.assertEquals(null, newFractionOneHalf().divide(new Fraction()));
	}

	@Test
	public void testDivideByNegative() throws Exception {
		Assert.assertEquals(new Fraction(-1), newFractionOne().divide(new Fraction(-1)));
	}

	@Test
	public void testDivideByDoubleNegative() throws Exception {
		Assert.assertEquals(newFractionOne(), new Fraction(-1).divide(new Fraction(-1)));
	}

	@Test
	public void testMultiply() throws Exception {
		Assert.assertEquals(new Fraction(1, 4), newFractionOneHalf().multiply(newFractionOneHalf()));
	}

	@Test
	public void testMultiplyBigNumbers() throws Exception {
		Assert.assertEquals(new Fraction(10000), new Fraction(100, 1).multiply(new Fraction(3000, 30)));
	}

	@Test
	public void testMultiplyByZero() throws Exception {
		Assert.assertEquals(new Fraction(), new Fraction(0, 3).multiply(newFractionOneHalf()));
	}

	@Test
	public void testMultiplyByNegative() throws Exception {
		Assert.assertEquals(new Fraction(1, -2), new Fraction(-1).multiply(newFractionOneHalf()));
	}

	@Test
	public void testMultiplyDoubleNegative() throws Exception {
		Assert.assertEquals(new Fraction(2), new Fraction(-1).multiply(new Fraction(-2)));
	}

	private Fraction newFractionOne() {
		return new Fraction(1);
	}

	private Fraction newFractionOneHalf() {
		return new Fraction(1, 2);
	}
}
