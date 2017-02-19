package patterns.decorator.retry;

import java.util.Optional;

class RetriedExecution implements Execution<Optional<String>> {
	private final int maxRetries;
	private final Execution<Optional<String>> origin;

	RetriedExecution(Execution<Optional<String>> origin, int maxRetries) {
		this.origin = origin;
		this.maxRetries = maxRetries;
	}

	public Optional<String> execute(String parameter) {
		int currentRetries = 0;
		Optional<String> result = Optional.empty();
		while (currentRetries < maxRetries && !result.isPresent()) {
			try {
				result = origin.execute(parameter);
			} catch (SomeException e) {
				System.err.println("#exception=" + e.getMessage());
			}
			currentRetries++;
		}
		return result;
	}
}
