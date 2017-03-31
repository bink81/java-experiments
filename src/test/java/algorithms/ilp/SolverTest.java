package algorithms.ilp;

import org.junit.Assert;
import org.junit.Test;

public class SolverTest {
	private static final int TIMEOUT_IN_MILISECONDS = 100;

	@Test
	public void solveExampleProblemWithBreadthFirst() {
		solveExampleProblem(TraversingStrategy.BREADTH_FIRST);
	}

	@Test
	public void solveExampleProblemWithDepthFirst() {
		solveExampleProblem(TraversingStrategy.DEPTH_FIRST);
	}

	private void solveExampleProblem(TraversingStrategy traversingStrategy) {
		double[][] a = { { 1, 2, 1, 0 }, { 1, 0, 1, 1 }, { 0, 0, 0, 1 } };
		double[] b = new double[] { 2, 1, 1 };
		double[] c = new double[] { 3, 4, 10, 15 };

		// x1 + 2*x2 + x3 <= 2
		// x1 + x3 <= 1
		// x4 <= 1
		// Maximize 3*x1+4*x2+10*x3+15*x4

		Solver solver = new Solver(a, b, c, TIMEOUT_IN_MILISECONDS, traversingStrategy);

		int[] solve = solver.solve();

		Assert.assertFalse(solver.hasReachedTimout());
		Assert.assertEquals(solve[0], 0);
		Assert.assertEquals(solve[1], 1);
		Assert.assertEquals(solve[2], 0);
		Assert.assertEquals(solve[3], 1);
	}

	/**
	 * Solves work case used at http://web.mit.edu/15.053/www/AMP-Chapter-09.pdf (section 9.5)
	 */
	@Test
	public void solveSimpleProblemWithBreadthFirst() {
		solveSimpleProblem(TraversingStrategy.BREADTH_FIRST);
	}

	@Test
	public void solveSimpleProblemWithDepthFirst() {
		solveSimpleProblem(TraversingStrategy.DEPTH_FIRST);
	}

	private void solveSimpleProblem(TraversingStrategy traversingStrategy) {
		double[][] a = { { 1, 1 }, { 5, 9 } };
		double[] b = new double[] { 6, 45 };
		double[] c = new double[] { 5, 8 };

		// x1 + x2 <= 6
		// 5*x1 + 9*x2 <= 45
		// Maximize 5*x1+8*x2

		Solver solver = new Solver(a, b, c, TIMEOUT_IN_MILISECONDS, traversingStrategy);

		int[] solve = solver.solve();

		Assert.assertFalse(solver.hasReachedTimout());
		Assert.assertEquals(solve[0], 0);
		Assert.assertEquals(solve[1], 5);
	}
}
