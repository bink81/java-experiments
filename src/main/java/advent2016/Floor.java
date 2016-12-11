package advent2016;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;

public class Floor {
	static final String GENERATOR = "generator";
	static final String MICROCHIP = "microchip";

	private final Set<String> microchips;
	private final Set<String> generators;

	public Floor() {
		microchips = new HashSet<>();
		generators = new HashSet<>();
	}

	public Floor(Floor floor) {
		microchips = Sets.newHashSet(floor.getMicrochips());
		generators = Sets.newHashSet(floor.getGenerators());
	}

	public boolean valid() {
		for (String microchip : microchips) {
			if (!generators.contains(microchip) && !generators.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	// public Set<String> getItems() {
	// Set<String> s = Sets.newHashSet(getMicrochips());
	// s.addAll(getGenerators());
	// return s;
	// }

	public Set<String> getMicrochips() {
		return Sets.newHashSet(microchips);
	}

	public Set<String> getGenerators() {
		return Sets.newHashSet(generators);
	}

	public void addGenerator(String newGenerator) {
		generators.add(newGenerator);
	}

	// public void addItem(String item) {
	// if (item.endsWith(GENERATOR)) {
	// addGenerator(item);
	// } else {
	// addMicrochip(item);
	// }
	// }
	//
	// public void removeItem(String item) {
	// if (item.endsWith(GENERATOR)) {
	// removeGenerator(item);
	// } else {
	// removeMicrochip(item);
	// }
	// }

	public void removeMicrochip(String item) {
		microchips.remove(item);
	}

	public void removeGenerator(String item) {
		generators.remove(item);
	}

	public void addMicrochip(String newMicrochip) {
		microchips.add(newMicrochip);
	}

	@Override
	public String toString() {
		return "Floor [m=" + microchips + ", g=" + generators + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(microchips, generators);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Floor) {
			Floor that = (Floor) object;
			return Objects.equal(this.microchips, that.microchips)
					&& Objects.equal(this.generators, that.generators);
		}
		return false;
	}
}
