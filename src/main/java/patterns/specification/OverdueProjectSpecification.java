package patterns.specification;

public class OverdueProjectSpecification implements ProjectSpecification {
	@Override
	public boolean isSatisfiedBy(Project project) {
		// Business rules extracted from Project.isOverdue() method
		return true;
	}
}
