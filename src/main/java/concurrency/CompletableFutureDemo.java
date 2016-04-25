package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureDemo {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureDemo.class);

	public static void main(String[] args) {
		new CompletableFutureDemo().init(args);
	}

	public void init(String[] args) {
		CompletableFuture<String> completableFuture =
				createCompletableFuture().thenApply((Integer result) -> {
					int transformedResult = result * 100;
					return transformedResult;
				}).thenApply(result -> "Result: " + result);

		try {
			LOGGER.info("{}", completableFuture.get());
		} catch (InterruptedException | ExecutionException ex) {
			LOGGER.error("CompletableFutureTest", ex);
		}
	}

	private CompletableFuture<Integer> createCompletableFuture() {
		CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
			try {
				// test delay
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			} catch (InterruptedException e) {
			}
			return 20;
		});
		return futureCount;
	}
}
