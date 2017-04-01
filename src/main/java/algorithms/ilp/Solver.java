package algorithms.ilp;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.Set;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.PivotSelectionRule;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import com.google.common.base.Optional;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

/**
 * This class is used to solve integer linear programming problems, i.e.
 * problems that can be expressed as
 * <ul>
 * <li>maximize function c<sup>T</sup>x (T = transpose)</li>
 * <li>with constraint Ax <= b</li>
 * <li>and x >= 0</li>
 * <li>with x vector of integers</li>
 * </ul>
 * with A matrix of integers, b, c vectors of integers.
 * 
 * @see https://en.wikipedia.org/wiki/Integer_programming
 * @see https://en.wikipedia.org/wiki/Branch_and_bound
 */
public final class Solver {

	private static final double EPSILON = Math.pow(10, -6);

	private final double[][] aMatrix;

	private final double[] bVector;

	private final double[] cVector;

	private final double timeout;

	private Optional<PointValuePair> bestSolutionSoFar = Optional.absent();

	private Long start;

	private boolean reachedTimeout = false;

	private final TraversingStrategy type;

	private final Deque<SubSpaceSolver> queue = Queues.newArrayDeque();

	/**
	 * @param aMatrix
	 *          the matrix A so that Ax <= b
	 * @param bVector
	 *          the vector b so that Ax <= b
	 * @param cVector
	 *          the coefficients for the function to maximize cTx
	 * @param timeout
	 *          (in milliseconds) the timeout for the algorithm iteration. When
	 *          reached, we will use the best solution we got within the time,
	 *          even though we can't determine if it is the theoretical best.
	 */
	public Solver(double[][] aMatrix, double[] bVector, double[] cVector, double timeout,
			TraversingStrategy traversingStrategy) {
		super();
		this.timeout = timeout;
		this.type = traversingStrategy;

		checkArgument(aMatrix.length == bVector.length);
		checkArgument(aMatrix[0].length == cVector.length);

		checkFoZeroRowsAndColumns(aMatrix);

		this.aMatrix = new double[aMatrix.length][];
		for (int i = 0; i < aMatrix.length; i++) {
			this.aMatrix[i] = aMatrix[i].clone();
		}
		this.bVector = bVector.clone();
		this.cVector = cVector.clone();
	}

	/** Check that the matrix does not have any zeroes row or column */
	private static void checkFoZeroRowsAndColumns(double[][] aMatrix) {
		RealMatrix realMatrix = new Array2DRowRealMatrix(aMatrix);
		checkNonZeroColumns(aMatrix, realMatrix);
	}

	private static void checkNonZeroColumns(double[][] aMatrix, RealMatrix realMatrix) {
		double[] zeroesColumn = new double[aMatrix.length];
		for (int i = 0; i < aMatrix[0].length; i++) {
			checkArgument(!realMatrix.getColumn(i).equals(zeroesColumn));
		}
	}

	/**
	 * Computes an integer solution for the problem.
	 * 
	 * @return the array x that maximizes the function under the given
	 *         constraints.
	 */
	public int[] solve() {
		reset();

		// Linear constraints (Ax <= b)
		Set<LinearConstraint> constraints = Sets.newHashSetWithExpectedSize(bVector.length);
		for (int i = 0; i < bVector.length; i++) {
			constraints.add(new LinearConstraint(aMatrix[i], Relationship.LEQ, bVector[i]));
		}

		/*
		 * We are going to solve the Linear programming problem (where x is an array
		 * of double) recursively on subspaces of the feasible space, and take the
		 * best integer solution we can reach in the given timeout.
		 */
		start = System.currentTimeMillis();
		queue.offer(new SubSpaceSolver(constraints));

		// This could use several threads if needed
		while (!queue.isEmpty()) {
			queue.poll().solve();
		}

		checkState(bestSolutionSoFar.isPresent(), "No solution found in the allowed time");
		double[] bestSolution = bestSolutionSoFar.get().getPoint();
		return convertToIntegerArray(bestSolution);
	}

	private int[] convertToIntegerArray(double[] bestCoefs) {
		int[] result = new int[bestCoefs.length];
		for (int i = 0; i < bestCoefs.length; i++) {
			Double coef = bestCoefs[i];
			result[i] = (int) Math.round(coef);
		}
		return result;
	}

	private void reset() {
		reachedTimeout = false;
		bestSolutionSoFar = Optional.absent();
	}

	public final class SubSpaceSolver {

		private final Set<LinearConstraint> constraints;

		public SubSpaceSolver(Set<LinearConstraint> constraints) {
			super();
			this.constraints = constraints;
		}

		public void solve() {
			try {
				solveOnSubSpace(constraints);
			} catch (NoFeasibleSolutionException e) {
				// If there is no feasible solution for this space, just skip it
				return;
			}
		}
	}

	public void solveOnSubSpace(Set<LinearConstraint> constraints) {
		if (System.currentTimeMillis() - start > timeout) {
			reachedTimeout = true;
			return;
		}

		LinearObjectiveFunction functionToBeMaximized = new LinearObjectiveFunction(cVector, 0);
		PointValuePair optimize = new SimplexSolver().optimize(functionToBeMaximized,
				new LinearConstraintSet(constraints), GoalType.MAXIMIZE, PivotSelectionRule.BLAND,
				new NonNegativeConstraint(true));
		Optional<Double> maxSoFar = getMaxSoFar();
		if (maxSoFar.isPresent() && maxSoFar.get().compareTo(optimize.getValue()) >= 0) {
			// We won't find any better on this subspace
			return;
		}

		/*
		 * Once we have a solution for the LP problem (not necessarily integer), we
		 * subdivide the feasible region in 2 like described in Integer Programming,
		 * chapter 9
		 */
		double[] solution = optimize.getPoint();
		for (int i = solution.length - 1; i >= 0; i--) {
			int indexOfGreatestElement = getIndexOfGreatestElement(solution, i);
			double max = solution[indexOfGreatestElement];
			double round = Math.round(max);
			if (Math.abs(max - round) < EPSILON) {
				if (i == 0) {
					// All elements in x are integer. This is a potential solution to the
					// ILP
					// problem.
					if (!maxSoFar.isPresent() || maxSoFar.get().compareTo(optimize.getValue()) < 0) {
						updateBestSolutionSoFar(optimize);
					}
				}
			} else {
				// Separate space and start again
				double[] selectMaxResult = new double[cVector.length];
				selectMaxResult[indexOfGreatestElement] = 1;

				// Here we have a solution with x[g]=d is a double. But we are looking
				// for integers.
				// We will search the solution on 2 sub-regions: x[g] <= floor(d) and
				// x[g] >=
				// ceil(d).
				// For this we just need to add a linear constraint to the existing
				// ones.
				// See documentation mentioned above.
				LinearConstraint constraintBelow = new LinearConstraint(selectMaxResult, Relationship.LEQ,
						Math.floor(max));
				Set<LinearConstraint> constraintsBelow = Sets.newHashSet(constraints);
				constraintsBelow.add(constraintBelow);
				addToQueue(new SubSpaceSolver(constraintsBelow));

				LinearConstraint constraintAbove = new LinearConstraint(selectMaxResult, Relationship.GEQ,
						Math.ceil(max));
				Set<LinearConstraint> constraintsAbove = Sets.newHashSet(constraints);
				constraintsAbove.add(constraintAbove);
				addToQueue(new SubSpaceSolver(constraintsAbove));
				break;
			}
		}
	}

	private void addToQueue(SubSpaceSolver solver) {
		if (type == TraversingStrategy.DEPTH_FIRST) {
			queue.addFirst(solver);
		} else {
			queue.add(solver);
		}
	}

	private void updateBestSolutionSoFar(PointValuePair optimize) {
		bestSolutionSoFar = Optional.of(optimize);
	}

	private Optional<Double> getMaxSoFar() {
		if (bestSolutionSoFar.isPresent()) {
			return Optional.of(bestSolutionSoFar.get().getValue());
		}
		return Optional.absent();
	}

	public boolean hasReachedTimout() {
		return reachedTimeout;
	}

	/**
	 * Gets the index of the n-th greatest element in an array.
	 */
	private int getIndexOfGreatestElement(double[] array, int n) {
		checkArgument(n >= 0);
		checkArgument(n < array.length);
		ArrayIndexComparator comparator = new ArrayIndexComparator(array);
		Integer[] indexes = comparator.createIndexArray();
		Arrays.sort(indexes, comparator);
		return indexes[n];
	}

	private static class ArrayIndexComparator implements Comparator<Integer> {
		private final double[] array;

		public ArrayIndexComparator(double[] array) {
			this.array = array;
		}

		public Integer[] createIndexArray() {
			Integer[] indexes = new Integer[array.length];
			for (int i = 0; i < array.length; i++) {
				indexes[i] = i;
			}
			return indexes;
		}

		@Override
		public int compare(Integer index1, Integer index2) {
			return Double.compare(array[index1], array[index2]);
		}
	}
}
