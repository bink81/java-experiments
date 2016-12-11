package advent2016;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day11 {

	State start = new State(null);

	public long task1(final File file) throws IOException {
		Files.lines(file.toPath()).forEach(line -> parse(line));

		String p = start.getFloors().stream().map(floor -> floor.toString())
				.collect(Collectors.joining("\n"));
		// FIXME RM:p NOCOMMIT
		System.err.println("#floors=\n" + p);
		return shuffle();
	}

	private Object parse(String line) {
		Pattern p = Pattern.compile("The (\\w+) floor contains(.+).");
		Matcher m = p.matcher(line.trim());
		if (m.find()) {
			Floor floor = new Floor();
			start.getFloors().add(floor);
			if (!m.group(2).equals(" nothing relevant")) {
				String names = m.group(2);
				String[] and = names.split(" and a ");
				String[] split = and[0].split(" a ");
				for (int i = 0; i < split.length; i++) {
					String name = split[i];
					if (name.length() > 0) {
						addItem(floor, name);
					}
				}
				if (and.length > 1) {
					addItem(floor, and[and.length - 1]);
				}
			}
		}
		return null;
	}

	private void addItem(Floor floor, String name) {
		if (name.endsWith(Floor.MICROCHIP)) {
			String[] split = name.split("-");
			floor.addMicrochip(split[0]);
		} else {
			String[] split = name.split(" ");
			floor.addGenerator(split[0]);
		}
	}

	private long shuffle() {
		Set<State> explored = new HashSet<>();
		Queue<State> q = new ArrayDeque<>();
		q.add(start);
		while (!q.isEmpty()) {
			State head = q.poll();
			if (head.isDone()) {
				long steps = 0;
				State temp = head;
				while (temp != null) {
					// FIXME RM:temp NOCOMMIT
					System.err.println("#temp=" + temp);
					temp = temp.getParent();
					steps++;
				}
				return steps;
			}
			int elevatorFloor = head.getElevatorFloor();
			List<Integer> possibleElevatorChanges = new ArrayList<>();
			if (elevatorFloor < State.NUMBER_OF_FLOORS - 1) {
				possibleElevatorChanges.add(1);
			}
			if (elevatorFloor > 0) {
				possibleElevatorChanges.add(-1);
			}

			List<Floor> floors;
			floors = cloneList(head.getFloors());
			for (int e : possibleElevatorChanges) {
				Floor floor = floors.get(head.getElevatorFloor());
				for (String item1 : floor.getMicrochips()) {
					floors = cloneList(head.getFloors());
					int newElevatorLevel = e + head.getElevatorFloor();
					floors.get(newElevatorLevel).addMicrochip(item1);
					floors.get(head.getElevatorFloor()).removeMicrochip(item1);
					boolean success = appendNewStateIfValid(q, head, floors, explored, newElevatorLevel);
					if (success) {
						for (String item2 : floor.getMicrochips()) {
							List<Floor> floors2 = cloneList(floors);
							if (!item1.equals(item2)) {
								floors2.get(newElevatorLevel).addMicrochip(item2);
								floors2.get(head.getElevatorFloor()).removeMicrochip(item2);
								appendNewStateIfValid(q, head, floors2, explored, newElevatorLevel);
							}
						}
					}
				}
			}
			floors = cloneList(head.getFloors());
			for (int e : possibleElevatorChanges) {
				Floor floor = floors.get(head.getElevatorFloor());
				for (String item1 : floor.getMicrochips()) {
					floors = cloneList(head.getFloors());
					int newElevatorLevel = e + head.getElevatorFloor();
					for (String item2 : floor.getGenerators()) {
						List<Floor> floors2 = cloneList(floors);
						floors2.get(newElevatorLevel).addMicrochip(item1);
						floors2.get(head.getElevatorFloor()).removeMicrochip(item1);
						floors2.get(newElevatorLevel).addGenerator(item2);
						floors2.get(head.getElevatorFloor()).removeGenerator(item2);
						appendNewStateIfValid(q, head, floors2, explored, newElevatorLevel);
					}
				}
			}
			floors = cloneList(head.getFloors());
			for (int e : possibleElevatorChanges) {
				Floor floor = floors.get(head.getElevatorFloor());
				for (String item1 : floor.getGenerators()) {
					floors = cloneList(head.getFloors());
					int newElevatorLevel = e + head.getElevatorFloor();
					floors.get(newElevatorLevel).addGenerator(item1);
					floors.get(head.getElevatorFloor()).removeGenerator(item1);
					boolean success = appendNewStateIfValid(q, head, floors, explored, newElevatorLevel);
					if (success) {
						for (String item2 : floor.getGenerators()) {
							List<Floor> floors2 = cloneList(floors);
							if (!item1.equals(item2)) {
								floors2.get(newElevatorLevel).addGenerator(item2);
								floors2.get(head.getElevatorFloor()).removeGenerator(item2);
								appendNewStateIfValid(q, head, floors2, explored, newElevatorLevel);
							}
						}
					}
				}
			}
			floors = cloneList(head.getFloors());
			for (int e : possibleElevatorChanges) {
				floors = cloneList(head.getFloors());
				Floor floor = floors.get(head.getElevatorFloor());
				for (String item1 : floor.getGenerators()) {
					int newElevatorLevel = e + head.getElevatorFloor();
					for (String item2 : floor.getMicrochips()) {
						List<Floor> floors2 = cloneList(floors);
						floors2.get(newElevatorLevel).addGenerator(item1);
						floors2.get(head.getElevatorFloor()).removeGenerator(item1);
						floors2.get(newElevatorLevel).addMicrochip(item2);
						floors2.get(head.getElevatorFloor()).removeMicrochip(item2);
						appendNewStateIfValid(q, head, floors2, explored, newElevatorLevel);
					}
				}
			}
			explored.add(head);
		}
		return -1;
	}

	public static List<Floor> cloneList(List<Floor> list) {
		List<Floor> clone = new ArrayList<Floor>(list.size());
		for (Floor floor : list)
			clone.add(new Floor(floor));
		return clone;
	}

	private boolean appendNewStateIfValid(final Queue<State> q, final State head,
			final List<Floor> floors, final Set<State> explored, int newElevatorLevel) {
		State newState = new State(head, floors, newElevatorLevel);
		if (newState.valid() && !q.contains(newState) && !explored.contains(newState)) {
			// FIXME RM:newState NOCOMMIT
			System.err.println("#newState=" + newState);
			q.add(newState);
			return true;
		}
		return false;
	}
}
