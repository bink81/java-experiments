package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithms.MathUtils;

/**
 * This class is meant for algorithm complexity analysis. Once initiated, run it with a problem size
 * of one, as a base reference, and analyze a bigger input size. Afterward the estimateComplexity
 * method should estimate your algorithm complexity.
 */
public class ComplexityAnalyzer {
	private static final Logger logger = LoggerFactory.getLogger(ComplexityAnalyzer.class);

	private final List<Long> timestamps = new ArrayList<>();

	private final List<Integer> iterations = new ArrayList<>();

	private final List<Long> deltas = new ArrayList<>();

	public ComplexityAnalyzer() {
		addIteration(0);
	}

	public void addIteration(Integer iterations) {
		long now = System.nanoTime();
		long average = 0;
		if (!getTimestamps().isEmpty()) {
			int lastIndex = getTimestamps().size() - 1;
			long lastDelta = now - getTimestamps().get(lastIndex);
			average = lastDelta;
		}
		getDeltas().add(average);
		getIterations().add(iterations);
		getTimestamps().add(now);
		if (getTimestamps().size() != getIterations().size()) {
			logger.error(
				"After addition of label '{}', size of timestamps ({}) does not match size of labels ({}) ",
				new String[] { iterations.toString(), getTimestamps().size() + "",
						getIterations().size() + "" });
		}
	}

	public void report() {
		System.out.println("    Delta[ns] | Iterations | Average");
		System.out.println("--------------|------------|-----------");
		for (int i = 1; i < getTimestamps().size(); i++) {
			long delta = getTimestamps().get(i) - getTimestamps().get(i - 1);
			System.out.format(
				"%13d | %10s | %s\n", delta, getIterations().get(i),
				getDeltas().get(i) / iterations.get(i));
		}
		System.out.println("Total time: " + getTotalExecutionTime() + "[ms]");
	}

	public long getTotalExecutionTime() {
		int lastIndex = getTimestamps().size() > 0 ? getTimestamps().size() - 1 : 0;
		if (lastIndex == 0) {
			return 0;
		}
		long deltaInNanoseconds = getTimestamps().get(lastIndex) - getTimestamps().get(0);
		return TimeUnit.NANOSECONDS.toMillis(deltaInNanoseconds);
	}

	public AlgorithmComplexity estimateComplexity() {
		Map<Long, AlgorithmComplexity> differences = new HashMap<>();
		long base = getDeltas().get(1); // result for one record
		long actual = getDeltas().get(2);// result for n records
		Integer n = getIterations().get(2);
		differences.put(
			calculateDifference(actual, base * Math.round(Math.log(n))), AlgorithmComplexity.LOG_N);
		differences.put(calculateDifference(actual, base * n), AlgorithmComplexity.LINEAR);
		differences.put(calculateDifference(actual, base * n * n), AlgorithmComplexity.QUADRATIC);
		differences.put(calculateDifference(actual, base * n * n * n), AlgorithmComplexity.CUBIC);

		long min = MathUtils.minimum(differences.keySet());
		return differences.get(min);
	}

	private long calculateDifference(long actual, long estimate) {
		long diff = Math.abs(estimate - actual);
		return diff;
	}

	List<Long> getTimestamps() {
		return timestamps;
	}

	List<Integer> getIterations() {
		return iterations;
	}

	List<Long> getDeltas() {
		return deltas;
	}
}