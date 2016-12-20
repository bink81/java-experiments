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

	public long task2(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));
		int[] baseLocations = extractLocations();

		int[] extendedLocations = Arrays.copyOf(baseLocations, baseLocations.length + 4);
		return shuffle(extendedLocations);
	}

	private int[] extractLocations() {
		List<Integer> itemLocations = new ArrayList<>();
		for (Entry<String, Integer> e : microchips.entrySet()) {
			itemLocations.add(e.getValue());
			itemLocations.add(generators.get(e.getKey()));
		}
		return Ints.toArray(itemLocations);
	}

	private Object parse(String line) {
		Pattern p = Pattern.compile("The \\w+ floor contains (.+).");
		Matcher m = p.matcher(line.trim());
		if (m.find()) {
			if (!m.group(1).equals("nothing relevant")) {
				String names = m.group(1);
				String[] and = names.split("and ");
				String[] split = and[0].split(", ");
				for (int i = 0; i < split.length; i++) {
					String name = split[i];
					if (name.length() > 0) {
						addItem(name, numberOfFloors);
					}
				}
				if (and.length > 1) {
					String lastName = and[and.length - 1];
					addItem(lastName, numberOfFloors);
				}
			}
			numberOfFloors++;
		}
		return null;
	}

	private void addItem(final String aname, final int floor) {
		String name = aname.trim().substring(2);
		if (name.endsWith(MICROCHIP)) {
			String[] split = name.split(DASH);
			microchips.put(shorten(split[0]), floor);
		}
		else {
			String[] split = name.split(" ");
			String split1 = split[0].split(DASH)[0];
			generators.put(shorten(split1), floor);
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
		Queue<State> list = new LinkedList<>();
		list.add(new State(itemLocations, 0, 0));
		while (!list.isEmpty()) {
			State state = list.poll();
			if (isAllowedState(state.itemLocations)) {
				String newStateString = state.getInternalStringRepresentation();
				if (!exploredStates.contains(newStateString)) {
					exploredStates.add(newStateString);
					if (newStateString.equalsIgnoreCase(finalStateString)) {
						return state.depth;
					}

					for (int i = 0; i < state.itemLocations.length; i++) {
						if (state.itemLocations[i] == state.elevator) {
							state.itemLocations[i]--;
							addState(list, state, state.elevator - 1);
							state.itemLocations[i] += 2;
							addState(list, state, state.elevator + 1);
							state.itemLocations[i]--;

							for (int j = i + 1; j < state.itemLocations.length; j++) {
								if (state.itemLocations[j] == state.elevator) {
									state.itemLocations[j]--;
									state.itemLocations[i]--;
									addState(list, state, state.elevator - 1);
									state.itemLocations[j] += 2;
									state.itemLocations[i] += 2;
									addState(list, state, state.elevator + 1);
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