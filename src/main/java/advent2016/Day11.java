package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.primitives.Ints;

public class Day11 {
	private static final String DASH = "-";

	private static final String MICROCHIP = "microchip";

	private Map<String, Integer> microchips = new HashMap<>();

	private Map<String, Integer> generators = new HashMap<>();

	private int numberOfFloors = 0;

	public long task1(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		return shuffle(extractLocations());
	}

	public long task2(final File file, final int numberOfNewPairsOnFloor0) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		int[] baseLocations = extractLocations();
		int[] extendedLocations =
				Arrays.copyOf(baseLocations, baseLocations.length + numberOfNewPairsOnFloor0);
		return shuffle(extendedLocations);
	}

	private Object parse(String line) {
		Pattern pattern = Pattern.compile("The \\w+ floor contains (.+).");
		Matcher matcher = pattern.matcher(line.trim());
		if (matcher.find()) {
			if (!matcher.group(1).equals("nothing relevant")) {
				String[] splitByAnd = matcher.group(1).split("and ");
				String[] splitByComma = splitByAnd[0].split(", ");
				for (int i = 0; i < splitByComma.length; i++) {
					String nameWithPreposition = splitByComma[i];
					if (nameWithPreposition.length() > 0) {
						addItem(nameWithPreposition, numberOfFloors);
					}
				}
				if (splitByAnd.length > 1) {
					String nameWithPreposition = splitByAnd[splitByAnd.length - 1];
					addItem(nameWithPreposition, numberOfFloors);
				}
			}
			numberOfFloors++;
		}
		return null;
	}

	private int[] extractLocations() {
		List<Integer> itemLocations = new ArrayList<>(generators.size() + microchips.size());
		for (Entry<String, Integer> entry : microchips.entrySet()) {
			itemLocations.add(entry.getValue());
			itemLocations.add(generators.get(entry.getKey()));
		}
		return Ints.toArray(itemLocations);
	}

	private void addItem(final String nameWithPreposition, final int floor) {
		String name = nameWithPreposition.trim().substring(2);
		if (name.endsWith(MICROCHIP)) {
			String[] splitDash = name.split(DASH);
			microchips.put(shorten(splitDash[0]), floor);
		}
		else {
			String[] splitSpace = name.split(" ");
			String splitDash = splitSpace[0].split(DASH)[0];
			generators.put(shorten(splitDash), floor);
		}
	}

	private String shorten(final String split1) {
		return split1.substring(0, 3).toUpperCase();
	}

	public int shuffle(final int[] itemLocations) {
		Set<String> exploredStates = new HashSet<>();
		String finalStateString =
				new State(calculateFinalState(itemLocations), 666, numberOfFloors - 1)
					.getInternalStringRepresentation();
		Queue<State> queue = new LinkedList<>();
		queue.add(new State(itemLocations, 0, 0));
		while (!queue.isEmpty()) {
			State state = queue.poll();
			if (isAllowedState(state.itemLocations)) {
				String newStateString = state.getInternalStringRepresentation();
				if (newStateString.equalsIgnoreCase(finalStateString)) {
					return state.depth;
				}
				if (!exploredStates.contains(newStateString)) {
					exploredStates.add(newStateString);
					for (int i = 0; i < state.itemLocations.length; i++) {
						if (state.itemLocations[i] == state.elevator) {
							state.itemLocations[i]--;
							addState(queue, state, state.elevator - 1);
							state.itemLocations[i] += 2;
							addState(queue, state, state.elevator + 1);
							state.itemLocations[i]--;
							// move second item
							for (int j = i + 1; j < state.itemLocations.length; j++) {
								if (state.itemLocations[j] == state.elevator) {
									state.itemLocations[j]--;
									state.itemLocations[i]--;
									addState(queue, state, state.elevator - 1);
									state.itemLocations[j] += 2;
									state.itemLocations[i] += 2;
									addState(queue, state, state.elevator + 1);
									state.itemLocations[j]--;
									state.itemLocations[i]--;
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}

	private void addState(final Queue<State> list, final State state, final int newElevatorLevel) {
		if (newElevatorLevel >= 0 && newElevatorLevel < numberOfFloors) {
			list.add(new State(state.itemLocations, state.depth + 1, newElevatorLevel));
		}
	}

	// everything on last floor
	private int[] calculateFinalState(final int[] itemLocations) {
		List<Integer> finalstate = new ArrayList<>(itemLocations.length);
		for (int i = 0; i < itemLocations.length; i++) {
			finalstate.add(numberOfFloors - 1);
		}
		return Ints.toArray(finalstate);
	}

	public boolean isAllowedState(final int[] itemLocations) {
		for (int i = 0; i < itemLocations.length; i += 2) {
			if (itemLocations[i] != itemLocations[i + 1]) {
				for (int j = 1; j < itemLocations.length; j += 2) {
					if (itemLocations[j] == itemLocations[i]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static class State {
		int[] itemLocations;

		int depth;

		int elevator;

		public State(int itemLocations[], int depth, int elevator) {
			this.itemLocations = Arrays.copyOf(itemLocations, itemLocations.length);
			this.depth = depth;
			this.elevator = elevator;
		}

		@Override
		public String toString() {
			return "State [itemLocations=" + Arrays.toString(itemLocations) + ", depth=" + depth
					+ ", elevator=" + elevator + "]";
		}

		public String getInternalStringRepresentation() { // depths is not part of the state
			StringBuilder sb = new StringBuilder();
			sb.append(elevator);
			for (int i = 0; i < itemLocations.length; i++) {
				sb.append(itemLocations[i]);
			}
			return sb.toString();
		}
	}
}