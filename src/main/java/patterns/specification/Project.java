package patterns.specification;

class Project {

	// business logic without using specification pattern adds complexity to the
	// entity that should better focus on it's state
	public boolean isOverdue() {
		return true;
	}
}
