package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureDemo {
	private static final int RESULT_MODIFIER = 100;

	private static final int RESULT_FROM_TASK = 20;

	private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

	public static void main(String[] args) {
		new CompletableFutureDemo().init(args);
	}

	public void init(String[] args) {
		CompletableFuture<String> completableFuture =
				createCompletableFuture().thenApply((Integer result) -> {
					int transformedResult = result * RESULT_MODIFIER;
					return transformedResult;
				}).thenApply(result -> "Result: " + result);

		try {
			logger.info("{}", completableFuture.get());
		} catch (InterruptedException | ExecutionException ex) {
			logger.error("CompletableFutureTest exception", ex);
		}
	}

	private CompletableFuture<Integer> createCompletableFuture() {
		CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
			try {
				// test delay
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			} catch (InterruptedException e) {
				logger.error("CompletableFutureDemo exception", e);
			}
			return RESULT_FROM_TASK;
		});
		return futureCount;
	}
}
