package utils;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringGeneratorTest {

	private static final int DUMMY_SIZE = 300;

	@Test(expected = IllegalArgumentException.class)
	public void testProduceDefaultStringWithNegativeSize() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(-1);
		g.produceString();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProduceDefaultStringWithWrongOrder() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(1, 'a', 'b');
		g.produceString();
	}

	@Test
	public void testProduceDefaultStringWithSizeZero() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(0);
		String actual = g.produceString();

		Assert.assertEquals(0, actual.length());
	}

	@Test
	public void testProduceDefaultString() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(DUMMY_SIZE);
		String actual = g.produceString();

		Assert.assertEquals(DUMMY_SIZE, actual.length());
	}

	@Test
	public void testProduceStringWithDigits() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(DUMMY_SIZE, '1', '0');
		String actual = g.produceString();

		Assert.assertEquals(DUMMY_SIZE, actual.length());
		Assert.assertTrue(actual.matches("\\d+"));
	}

	@Test
	public void testProduceStringWithLetters() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(DUMMY_SIZE, 'z', 'a');
		String actual = g.produceString();

		Assert.assertEquals(DUMMY_SIZE, actual.length());
		Assert.assertTrue(actual.matches("\\w+"));
		String lowercased = actual.toLowerCase();
		Assert.assertTrue(lowercased.equals(actual));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProduceStringWithSameLetter() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(DUMMY_SIZE, 'z', 'z');
		g.produceString();
	}

	@Test
	public void testProduceStringWithUpperCaseLetters() throws Exception {
		RandomStringGenerator g = new RandomStringGenerator(DUMMY_SIZE, 'Z', 'A');
		String actual = g.produceString();

		Assert.assertEquals(DUMMY_SIZE, actual.length());
		Assert.assertTrue(actual.matches("\\w+"));
		String uppercased = actual.toUpperCase();
		Assert.assertTrue(uppercased.equals(actual));
	}
}
