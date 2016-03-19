package patterns.producerconsumer;

import java.util.concurrent.Callable;

public class ExampleProducer implements Callable<ExampleProduct> {

	private String name;

	public ExampleProducer(String name) {
		this.name = name;
	}

	@Override
	public ExampleProduct call() throws Exception {
		return new ExampleProduct(name);
	}

}
