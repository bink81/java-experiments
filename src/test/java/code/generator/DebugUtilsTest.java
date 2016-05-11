package code.generator;

import static utils.CommonCode.ignoreException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(DebugUtilsTest.class);

	@Test
	public void testIgnoreExceptionForBlock() throws Exception {
		ignoreException(() -> {
			// some complex code here
			throw new Throwable("Example exception is thrown");
		});
		logThatExecutionContinuesDespiteTheException();
	}

	@SuppressWarnings("null") // We test this on purpose
	@Test
	public void testIgnoreExceptionWhenCallingMethod() throws Exception {
		String nullObject = null;
		ignoreException(nullObject::toString);
		logThatExecutionContinuesDespiteTheException();
	}

	private void logThatExecutionContinuesDespiteTheException() {
		logger.info("Ignored! Execution continues despite the exception");
	}

	@Test
	public void testLogEnvironmentProperties() throws Exception {
		DebugUtils.logEnvironmentProperties();
	}
}
