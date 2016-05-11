package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is meant for performance analysis. Once initialized, you can add new steps and see a
 * report or total execution time at the end.
 */
public class EventAnalyzer {
	private static final Logger logger = LoggerFactory.getLogger(EventAnalyzer.class);

	private final List<Long> timestamps = new ArrayList<>();

	private final List<String> labels = new ArrayList<>();

	public void before() {
		add("Before");
	}

	public void after() {
		add("After");
	}

	public void add(String label) {
		long currentTimestamp = getCurrentTimestamp();
		getTimestamps().add(currentTimestamp);
		getLabels().add(label);
		if (getTimestamps().size() != getLabels().size()) {
			logger.error(
				"After addition of label '{}', size of timestamps ({}) does not match size of labels ({}) ",
				new String[] { label, getTimestamps().size() + "", getLabels().size() + "" });
		}
	}

	private long getCurrentTimestamp() {
		return System.nanoTime();
	}

	public void report() {
		System.out.println("Number of registered events: " + getEventCount());
		System.out.println();
		System.out.println("Delta[ns] | Label");
		System.out.println("----------|------------------------");
		Long timestamp = null;
		for (int index = 0; index < getTimestamps().size(); index++) {
			long delta = timestamp != null ? getTimestamps().get(index) - timestamp : 0;
			System.out.format("%9d | %s\n", delta, getLabels().get(index));
			timestamp = getTimestamps().get(index);
		}
		System.out.println();
		System.out.println(
			"Difference between first and last event: " + getTotalExecutionTime() + "[ms]");
	}

	public long getTotalExecutionTime() {
		int lastIndex = timestamps.size() > 0 ? timestamps.size() - 1 : 0;
		if (lastIndex == 0) {
			return 0;
		}
		long deltaInNanoseconds = timestamps.get(lastIndex) - timestamps.get(0);
		return TimeUnit.NANOSECONDS.toMillis(deltaInNanoseconds);
	}

	public int getEventCount() {
		return getTimestamps().size();
	}

	List<Long> getTimestamps() {
		return timestamps;
	}

	List<String> getLabels() {
		return labels;
	}
}