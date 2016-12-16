package advent2016;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Day13 {

	private final int MAX_X = 55; // more than the required 50 step limit

	private final int MAX_Y = 55; // more than the required 50 step limit

	private final int START_X = 1;

	private final int START_Y = 1;

	private final int favoriteNumber;

	private final int destinationX;

	private final int destinationY;

	private final Queue<Position> queue = new ArrayDeque<>();

	private final boolean[][] walls = new boolean[MAX_Y + 1][MAX_X + 1];

	// rough estimate
	private final Set<Position> exploredPositions = new HashSet<>(MAX_X * MAX_Y / 2);

	public Day13(int favoriteNumber, int finalX, int finalY) {
		this.favoriteNumber = favoriteNumber;
		this.destinationX = finalX;
		this.destinationY = finalY;
	}

	public long calculateSteps() {
		createLabirynth();
		// showMap();
		return calculateStepsToReachPoint();
	}

	private void createLabirynth() {
		for (int y = 0; y < MAX_Y; y++) {
			for (int x = 0; x < MAX_X; x++) {
				int sum = x * x + 3 * x + 2 * x * y + y + y * y + favoriteNumber;
				String binaryString = Long.toBinaryString(sum);
				long countOfOnes = binaryString.chars().filter(c -> c == '1').count();// forEach(System.out::println);
				if (countOfOnes % 2 == 1) {
					walls[y][x] = true;
				}
			}
		}
	}

	private long calculateStepsToReachPoint() {
		queue.add(new Position(START_X, START_Y, 0));
		while (!queue.isEmpty()) {
			Position head = queue.poll();
			exploredPositions.add(head);
			if (head.getX() == destinationX && head.getY() == destinationY) {
				return head.getDepth();
			}
			addAvailablePositionsIfNew(head);
		}
		return -1;
	}

	private void addIfNew(int x, int y, Position parent) {
		Position newPosition = new Position(x, y, parent.getDepth() + 1);
		if (!walls[y][x] && !exploredPositions.contains(newPosition)
				&& !queue.contains(newPosition)) {
			queue.add(newPosition);
		}
	}

	public long calculateDisctinctReachableSteps(int maxSteps) {
		createLabirynth();
		queue.add(new Position(START_X, START_Y, 0));
		while (!queue.isEmpty()) {
			Position head = queue.poll();
			exploredPositions.add(head);
			if (head.getDepth() >= maxSteps) {
				break;
			}
			if (head.getX() == destinationX && head.getY() == destinationY) {
				return head.getDepth();
			}
			addAvailablePositionsIfNew(head);
		}

		return exploredPositions.size();
	}

	private void addAvailablePositionsIfNew(Position head) {
		if (head.getX() > 0) {
			addIfNew(head.getX() - 1, head.getY(), head);
		}
		if (head.getX() < MAX_X) {
			addIfNew(head.getX() + 1, head.getY(), head);
		}
		if (head.getY() > 0) {
			addIfNew(head.getX(), head.getY() - 1, head);
		}
		if (head.getY() < MAX_X) {
			addIfNew(head.getX(), head.getY() + 1, head);
		}
	}

	public void showMap() { // for debugging purposes
		for (int y = 0; y < MAX_Y; y++) {
			for (int x = 0; x < MAX_X; x++) {
				boolean pixelOn = walls[y][x];
				// Position position = new Position(x, y, 0);
				// if (explored.contains(position)) {
				// System.err.print('X');
				// }
				if (x == destinationX && y == destinationY) {
					System.err.print('X');
				}
				else {
					System.err.print(pixelOn ? '#' : '.');
				}
			}
			System.err.println();
		}
	}

	final class Position {
		private final int x;

		private final int y;

		private final int depth;

		public Position(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Position [x=" + getX() + ", y=" + getY() + ", depth=" + getDepth() + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getX();
			result = prime * result + getY();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (getX() != other.getX())
				return false;
			if (getY() != other.getY())
				return false;
			return true;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getDepth() {
			return depth;
		}
	}
}
