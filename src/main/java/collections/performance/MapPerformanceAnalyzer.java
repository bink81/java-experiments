package collections.performance;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapPerformanceAnalyzer {
	static volatile int sharedVolatileResource = 0;

	public static void main(String... args) {
		MapTester testers[] = { new MapTester(new HashMap<Integer, Integer>()),
				new MapTester(new TreeMap<Integer, Integer>()),
				new MapTester(new LinkedHashMap<Integer, Integer>()),
				new MapTester(new ConcurrentHashMap<Integer, Integer>()),
				new MapTester(new ConcurrentSkipListMap<Integer, Integer>()) };
		int sampleSizes[] = new int[] { 1, 3, 10, 30, 100, 300, 1000, 3000, 10_000 };
		initiateSharedVolatileResource();

		for (int i = 0; i < sampleSizes.length; i++) {
			int size = sampleSizes[i];
			System.out.println("\nTesting maps for size: " + size);
			initialize(testers, sampleSizes, size);
			warmup(testers);
			run(testers);
			report(testers);
		}
	}

	private static void initiateSharedVolatileResource() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					sharedVolatileResource++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	private static void initialize(final MapTester[] testers, final int[] sizes, final int size) {
		for (MapTester mapTester : testers) {
			mapTester.fillRandomNumbersTo(size);
		}
	}

	private static void warmup(final MapTester[] testers) {
		for (MapTester mapTester : testers) {
			while (sharedVolatileResource < sharedVolatileResource + 20) {
				mapTester.runForEach();
			}
		}
	}

	private static void run(final MapTester[] testers) {
		for (MapTester mapTester : testers) {
			mapTester.init();
			long previousTime = System.nanoTime();
			while (sharedVolatileResource < sharedVolatileResource + 30) {
				mapTester.runForEach();
			}
			mapTester.updateTime(previousTime, System.nanoTime());
		}
	}

	private static void report(final MapTester[] testers) {
		for (MapTester mapTester : testers) {
			mapTester.report();
		}
	}
}
