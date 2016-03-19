package patterns.producerconsumer;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class ExampleService {
	private static final Executor executor = Executors.newCachedThreadPool();
	private static final CompletionService<ExampleProduct> exampleCompletionService = new ExecutorCompletionService<ExampleProduct>(
			executor);

	private void start() {
		addConsumer("consumer1");
		addConsumer("consumer2");
		addConsumer("consumer3");
		addConsumer("consumer4");

		addProducer("producer1");
		addProducer("producer2");
		addProducer("producer3");
		addProducer("producer4");
	}

	private void addConsumer(String name) {
		ExampleConsumer consumer = new ExampleConsumer(name, getExampleCompletionService());
		new Thread(consumer).start();
	}

	private void addProducer(String name) {
		ExampleProducer producer = new ExampleProducer(name);
		getExampleCompletionService().submit(producer);
	}

	public Executor getExecutor() {
		return executor;
	}

	public CompletionService<ExampleProduct> getExampleCompletionService() {
		return exampleCompletionService;
	}

	public static void main(String[] args) {
		new ExampleService().start();
	}
}
