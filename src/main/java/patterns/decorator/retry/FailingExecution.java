package patterns.decorator.retry;

import java.util.Optional;

public class FailingExecution implements Execution<Optional<String>> {
	@Override
	public Optional<String> execute(String parameter) throws SomeException {
		throw new SomeException("failing with parameter: " + parameter);
	}
}
