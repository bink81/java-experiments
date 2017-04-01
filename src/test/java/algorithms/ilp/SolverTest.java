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

	/**
	 * I have 2 cokes (unit price 20), 1 sprite (unit price 10) and 1 mars bar
	 * (unit price 15) in my shopping basket. There are 3 possible campaigns:
	 * <ul>
	 * <li>buy 2 drinks get 10% rebate</li>
	 * <li>buy 1 coke 1 sprite and get 10kr rebate</li>
	 * <li>buy 1 sprite 1 mars bar and get the mars bar for free.</li>
	 * </ul>
	 * 
	 * The possible campaigns products combinations are
	 * <ul>
	 * <li>for the first campaign, 1 coke + 1 sprite (a), or 2 cokes (b)</li>
	 * <li>for the second campaign, 1 coke + 1 sprite (c)</li>
	 * <li>for the third, 1 sprite + 1 mars bar (d)</li>
	 * </ul>
	 * That gives the A matrix:
	 * <ul>
	 * <li>------------(a) (b) (c) (d)</li>
	 * <li>(coke) -----1---2---1---0</li>
	 * <li>(sprite) ----1---0---1---1</li>
	 * <li>(mars bar) -0---0---0---1</li>
	 * </ul>
	 * 
	 * Vector b is:
	 * <ul>
	 * <li>(coke) -----2</li>
	 * <li>(sprite) ----1</li>
	 * <li>(mars bar)-1</li>
	 * </ul>
	 * 
	 * And vector c (rebates) is:
	 * <ul>
	 * <li>(a) 3 [10% on a coke and a sprite]</li>
	 * <li>(b) 4 [10% on 2 cokes]</li>
	 * <li>(c) 10 [10 DKK rebate]</li>
	 * <li>(d) 15 [1 mars bar free]</li>
	 * </ul>
	 * The solution of the problem gives this array [0,1,0,1]. That means we need
	 * to use solutions (b) (1st campaign) and (d) (3rd campaign). We pick the
	 * corresponding products: 2 cokes for solution (b), 1 sprite and 1 mars bar
	 * for solution (d), and here is the best shopping solution!
	 * </p>
	 * <p>
	 * Extension: in order to take other rebates into account, we add a row in A
	 * per rebated product (privilege that is not from a bundle). The last
	 * elements in x will show how much times we should use these privileges, but
	 * we won't read those values.
	 * </p>
	 */
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
	 * Solves work case used at http://web.mit.edu/15.053/www/AMP-Chapter-09.pdf
	 * (section 9.5)
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
