package patterns.decorator.synchronization;

import org.junit.Assert;
import org.junit.Test;

public class SafeDomainObjectTest {

	private static final int NUMBER_OF_THREADS = 2;
	private static final int THREAD_ITERATIONS = 1000;

	// TODO: replace with an elegant result handling
	private static final int DELAY_FACTOR = 100;

	@Test
	public void testIncreaseCounter() throws Exception {
		DomainObject domainObject = new SafeDomainObject(new UnsafeDomainObject());
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			startThread(domainObject);
		}

		Thread.sleep(THREAD_ITERATIONS / DELAY_FACTOR);

		int actual = domainObject.getCounter();
		Assert.assertEquals(THREAD_ITERATIONS * NUMBER_OF_THREADS, actual);
	}

	private void startThread(final DomainObject domainObject) {
		new Thread() {
			public void run() {
				for (int i = 0; i < THREAD_ITERATIONS; i++) {
					domainObject.increaseCounter();
				}
			}
		}.start();
	}
}
