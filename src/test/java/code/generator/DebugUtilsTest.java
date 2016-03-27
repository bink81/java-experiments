package code.generator;

import static utils.CommonCode.ignoreException;

import org.junit.Test;

public class DebugUtilsTest {

	@Test
	public void testIgnoreExceptionForBlock() throws Exception {
		ignoreException(() -> {
			// complex code
			throw new Throwable("Exception is shown here just in case but the execution continues...");
		});
	}

	@SuppressWarnings("null") // We test this on purpose
	@Test
	public void testIgnoreExceptionForMethod() throws Exception {
		String nullObject = null;
		ignoreException(nullObject::toString);
	}
}
