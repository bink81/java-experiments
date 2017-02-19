package patterns.decorator.retry;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class RetriedExecutionTest {

	private static final String PARAMETER = "12";
	private static final int MAX_RETRIES = 3;

	@Test
	public void testExecuteFailure() throws Exception {
		Execution<Optional<String>> e = new RetriedExecution(new FailingExecution(), MAX_RETRIES);

		Optional<String> actual = e.execute(PARAMETER);

		Assert.assertEquals(Optional.empty(), actual);
	}

	@Test
	public void testExecuteSuccess() throws Exception {
		Execution<Optional<String>> e = new RetriedExecution(new SuccessExecution(), MAX_RETRIES);

		Optional<String> actual = e.execute(PARAMETER);

		Assert.assertEquals(Optional.of(SuccessExecution.SUCCESS_WITH_PARAMETER + PARAMETER), actual);
	}
}
