package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonCode {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCode.class);

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
			LOGGER.info("Exception caught: {} - {}", e.getClass().getSimpleName(), e.getMessage());
			LOGGER.debug("Exception stack: {}", e);
		}
	}
}
