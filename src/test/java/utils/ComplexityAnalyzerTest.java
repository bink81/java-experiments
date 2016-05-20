package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComplexityAnalyzerTest {

	private static final int ONE = 1;

	private ComplexityAnalyzer analyzer;

	@Before
	public void executedBeforeEach() {
		analyzer = new ComplexityAnalyzer();
	}

	@Test
	public void testLogarithmicAlgorithm() throws Exception {
		logarithmicAlgorithm(ONE);
		analyzer.addIteration(ONE);

		int n = 100;
		logarithmicAlgorithm(n);
		analyzer.addIteration(n);

		AlgorithmComplexity actual = analyzer.estimateComplexity();
		Assert.assertEquals(AlgorithmComplexity.LOG_N, actual);
	}

	private void logarithmicAlgorithm(int n) throws InterruptedException {
		Integer[] array = new Integer[n];
		for (int i = 1; i <= array.length; i *= 2) {
			Thread.sleep(ONE);
		}
	}

	@Test
	public void testLinearAlgorithm() throws Exception {
		linearAlgorithm(ONE);
		analyzer.addIteration(ONE);

		int n = 100;
		linearAlgorithm(n);
		analyzer.addIteration(n);

		AlgorithmComplexity actual = analyzer.estimateComplexity();
		Assert.assertEquals(AlgorithmComplexity.LINEAR, actual);
	}

	private void linearAlgorithm(int n) throws InterruptedException {
		Integer[] array = new Integer[n];
		for (int i = 0; i < array.length; i++) {
			Thread.sleep(ONE);
		}
	}

	@Test
	public void testQuadraticAlgorithm() throws Exception {
		quadraticAlgorithm(ONE);
		analyzer.addIteration(ONE);

		int n = 20;
		quadraticAlgorithm(n);
		analyzer.addIteration(n);

		AlgorithmComplexity actual = analyzer.estimateComplexity();
		Assert.assertEquals(AlgorithmComplexity.QUADRATIC, actual);
	}

	private void quadraticAlgorithm(int n) throws InterruptedException {
		Integer[] array = new Integer[n];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				Thread.sleep(ONE);
			}
		}
	}

	@Test
	public void testCubicAlgorithm() throws Exception {
		cubicAlgorithm(ONE);
		analyzer.addIteration(ONE);

		int n = 5;
		cubicAlgorithm(n);
		analyzer.addIteration(n);

		AlgorithmComplexity actual = analyzer.estimateComplexity();
		Assert.assertEquals(AlgorithmComplexity.CUBIC, actual);
	}

	private void cubicAlgorithm(int n) throws InterruptedException {
		Integer[] array = new Integer[n];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				for (int k = 0; k < array.length; k++) {
					Thread.sleep(ONE);
				}
			}
		}
	}

	@Test
	public void testNlogNAlgorithm() throws Exception {
		nlogNAlgorithm(ONE);
		analyzer.addIteration(ONE);

		int n = 50;
		int[] array = new int[n];//
		for (int i = 0; i < n; i++) {
			array[i] = n - i;
		}
		nlogNAlgorithm(n);
		analyzer.addIteration(n);

		AlgorithmComplexity actual = analyzer.estimateComplexity();
		Assert.assertEquals(AlgorithmComplexity.N_LOG_N, actual);
	}

	private void nlogNAlgorithm(int n) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			logarithmicAlgorithm(n);
		}
	}
}
