package utils;

import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonCode {
	private static final Logger logger = LoggerFactory.getLogger(CommonCode.class);

	@FunctionalInterface
	public interface CheckedRunnable {
		void run() throws Throwable;
	}

	/**
	 * This method helps to skip catch clauses that can be ignored
	 */
	public static void ignoreException(CheckedRunnable r) {
		try {
			r.run();
		} catch (Throwable e) {
			logger.info("Exception caught: {} - {}", e.getClass().getSimpleName(), e.getMessage());
			logger.debug("Exception stack: {}", e);
		}
	}

	public static void doubleForLoop(int lengthI, int lengthJ, BiConsumer<Integer, Integer> consumer) {
		for (int i = 0; i < lengthI; i++) {
			for (int k = 0; k < lengthJ; k++) {
				consumer.accept(i, k);
			}
		}
	}
}
