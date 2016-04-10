package utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventAnalyzer {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventAnalyzer.class);

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
			LOGGER.error(
					"After addition of label '{}', size of timestamps ({}) does not match size of labels ({}) ",
					new String[] { label, getTimestamps().size() + "", getLabels().size() + "" });
		}
	}

	private long getCurrentTimestamp() {
		return System.currentTimeMillis();
	}

	public void report() {
		System.out.println("Number of registered events: " + getEventCount());
		System.out.println();
		System.out.println("Delta[ms] | Label");
		System.out.println("----------|------------------------");
		Long timestamp = null;
		for (int index = 0; index < getTimestamps().size(); index++) {
			long delta = timestamp != null ? getTimestamps().get(index) - timestamp : 0;
			System.out.format("%9d | %s\n", delta, getLabels().get(index));
			timestamp = getTimestamps().get(index);
		}
		System.out.println();
		System.out.println("Difference between first and last event: " + getTotalDifference() + "[ms]");
	}

	public long getTotalDifference() {
		int lastIndex = timestamps.size() > 0 ? timestamps.size() - 1 : 0;
		return timestamps.get(lastIndex) - timestamps.get(0);
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