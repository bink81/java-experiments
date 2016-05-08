package concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class NonBlockingCounterTest {
	private static final int THREAD_NUMBER = 10;

	@Test
	public void testName() throws Exception {
		List<Future<Integer>> tasks = new ArrayList<Future<Integer>>();
		ExecutorService executor = createExecutor(tasks);
		executor.shutdown();
		waitUntilProcessingFinishes(executor);
		Set<Integer> results = assembleResults(tasks);
		checkIfEqual(tasks, results);
	}

	private ExecutorService createExecutor(List<Future<Integer>> tasks) {
		final NonBlockingCounter counter = new NonBlockingCounter();
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
		for (int i = 0; i < 500; i++) {
			Callable<Integer> worker = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int number = counter.increment();
					return number;
				}
			};
			Future<Integer> submit = executor.submit(worker);
			tasks.add(submit);
		}
		return executor;
	}

	private void waitUntilProcessingFinishes(ExecutorService executor) {
		while (!executor.isTerminated()) {
		}
	}

	private Set<Integer> assembleResults(List<Future<Integer>> list) {
		Set<Integer> results = new HashSet<Integer>();
		for (Future<Integer> future : list) {
			try {
				results.add(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	private void checkIfEqual(List<Future<Integer>> list, Set<Integer> set) {
		if (list.size() != set.size()) {
			throw new RuntimeException("Double-entries!!!");
		}
	}
}
