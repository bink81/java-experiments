package code.generator;

import static code.generator.DebugUtils.ignoreException;

import org.junit.Test;

public class DebugUtilsTest {

	@Test
	public void testIgnoreExceptionForBlock() throws Exception {
		ignoreException(() -> {
			// complex code
			throw new Throwable("Shown (just in case) but then continues...");
		});
	}

	@SuppressWarnings("null") // We test this on purpose
	@Test
	public void testIgnoreExceptionForMethod() throws Exception {
		String nullObject = null;
		ignoreException(nullObject::toString);
	}
}
