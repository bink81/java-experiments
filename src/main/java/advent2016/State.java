package advent2016;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class State {
	static final int NUMBER_OF_FLOORS = 4;

	private final List<Floor> floors;
	private final State parent;
	private final int elevatorFloor;

	public State(State parent) {
		this.parent = parent;
		this.floors = new ArrayList<>(NUMBER_OF_FLOORS);
		elevatorFloor = 0;
	}

	public State(State parent, List<Floor> floors, int elevatorFloor) {
		this.parent = parent;
		this.floors = Lists.newArrayList(floors);
		this.elevatorFloor = elevatorFloor;
	}

	public boolean valid() {
		for (Floor floor : getFloors()) {
			if (!floor.valid()) {
				return false;
			}
		}
		return true;
	}

	public boolean isDone() {
		for (int i = 0; i < getFloors().size() - 1; i++) {
			if (!getFloors().get(i).getGenerators().isEmpty()
					|| !getFloors().get(i).getMicrochips().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public State getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getFloors());
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof State) {
			State that = (State) object;
			return Objects.equal(this.getFloors(), that.getFloors());
		}
		return false;
	}

	@Override
	public String toString() {
		return "elevator: " + getElevatorFloor() + ", floors:\n"
				+ getFloors().stream().map(floor -> floor.toString()).collect(Collectors.joining("\n"));
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public int getElevatorFloor() {
		return elevatorFloor;
	}
}
