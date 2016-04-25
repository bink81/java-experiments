package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingCounter {
	private AtomicInteger value = new AtomicInteger();

	public int getValue() {
		return value.get();
	}

	public int increment() {
		// the below is equal to "value.incrementAndGet()"
		int currentValue = value.get();
		int nextValue = currentValue + 1;
		while (!value.compareAndSet(currentValue, nextValue)) {
			currentValue = value.get();
		}
		return currentValue + 1;
	}
}
