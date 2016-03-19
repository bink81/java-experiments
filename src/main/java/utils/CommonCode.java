package utils;

public class CommonCode {

	@FunctionalInterface
	public interface CheckedRunnable {
		void run() throws Throwable;
	}

	// This method helps skipping useless catch clauses
	public static void ignoreException(CheckedRunnable r) {
		try {
			r.run();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
