package wrappers.string;

import org.junit.Test;

public class FirstNameTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullValue() throws Exception {
		FirstName.of(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyValue() throws Exception {
		FirstName.of("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSpaceValue() throws Exception {
		FirstName.of(" ");
	}

	@Test
	public void testOneLetter() throws Exception {
		FirstName.of("A");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLowercaseLetter() throws Exception {
		FirstName.of("a");
	}
}
