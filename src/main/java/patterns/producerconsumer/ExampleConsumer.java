package patterns.producerconsumer;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ExampleConsumer implements Runnable {
	private final String name;
	private final CompletionService<ExampleProduct> completionService;

	public ExampleConsumer(String name, CompletionService<ExampleProduct> completionService) {
		this.name = name;
		this.completionService = completionService;
	}

	@Override
	public void run() {
		try {
			Future<ExampleProduct> fp = completionService.take();
			System.out.println(name + " consumed from " +
					fp.get().getCreatedBy());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}
}
