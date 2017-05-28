package collections.performance;

import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;

public class MapTester implements BiConsumer<Integer, Integer> {
	private static final int ONE_MILLION = 1_000_000;

	private long totalAccumulator; // mutable variable to prevent caching
	private long executionTime;
	private int currentSize;
	private long numberOfLoops;

	private final Map<Integer, Integer> map;

	MapTester(final Map<Integer, Integer> map) {
		this.map = map;
	}

	void init() {
		executionTime = 0;
		numberOfLoops = 0;
	}

	void fillRandomNumbersTo(final int size) {
		Random random = new Random(currentSize);
		while (currentSize < size) {
			Integer randomInteger = new Integer(random.nextInt());
			map.put(randomInteger, randomInteger);
			currentSize++;
		}
	}

	void runForEach() {
		map.forEach(this);
		numberOfLoops++;
	}

	void report() {
		System.out.printf("-> entries/s =% 11.3f for %s (total=%d)\n",
				currentSize * numberOfLoops / (double) (executionTime / ONE_MILLION),
				map.getClass().getSimpleName(), totalAccumulator);
	}

	void updateTime(final long startTime, final long currentTime) {
		executionTime += (currentTime - startTime);
	}

	@Override
	public void accept(final Integer value, final Integer unusedValue) {
		totalAccumulator += value;
	}
}
