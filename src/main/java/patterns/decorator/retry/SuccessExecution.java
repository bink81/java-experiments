package patterns.decorator.retry;

import java.util.Optional;

public class SuccessExecution implements Execution<Optional<String>> {
	public static final String SUCCESS_WITH_PARAMETER = "success with parameter: ";

	@Override
	public Optional<String> execute(String parameter) {
		return Optional.of(SUCCESS_WITH_PARAMETER + parameter);
	}
}
